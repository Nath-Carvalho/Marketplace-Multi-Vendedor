package br.com.marketplace.api.controller;

import br.com.marketplace.api.dto.ItemPedidoRequestDTO;
import br.com.marketplace.api.dto.PedidoRequestDTO;
import br.com.marketplace.api.repository.ClienteRepository;
import br.com.marketplace.api.service.PedidoService;
import br.com.marketplace.api.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final ClienteRepository clienteRepository;
    private final ProdutoService produtoService;

    public PedidoController(PedidoService pedidoService, ClienteRepository clienteRepository, ProdutoService produtoService) {
        this.pedidoService = pedidoService;
        this.clienteRepository = clienteRepository;
        this.produtoService = produtoService;
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("pedidos", pedidoService.listarTodos());
        //int erro = 10/0;
        return "pedidos/lista";
    }

    @GetMapping("/novo")
    public String mostrarFormulario(Model model) {
        PedidoRequestDTO dto = new PedidoRequestDTO();
        List<ItemPedidoRequestDTO> itensIniciais = new ArrayList<>();
        itensIniciais.add(new ItemPedidoRequestDTO());
        dto.setItens(itensIniciais);
        
        model.addAttribute("pedidoDTO", dto);
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("produtos", produtoService.listarTodos());
        return "pedidos/form";
    }

    @PostMapping("/salvar")
    public String realizarPedido(@Valid @ModelAttribute("pedidoDTO") PedidoRequestDTO request,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes,
                                 Model model) { 
        if (result.hasErrors()) {
            model.addAttribute("clientes", clienteRepository.findAll());
            model.addAttribute("produtos", produtoService.listarTodos());
            return "pedidos/form"; 
        }
        try {
            pedidoService.criarPedido(request);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Pedido realizado com sucesso!");
            return "redirect:/pedidos";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro: " + e.getMessage());
            return "redirect:/pedidos/novo";
        }
    }

    @GetMapping("/detalhes/{id}")
    public String verDetalhes(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("pedido", pedidoService.buscarPorId(id));
            return "pedidos/detalhes"; 
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Pedido não encontrado.");
            return "redirect:/pedidos";
        }
    }

    @GetMapping("/cancelar/{id}")
    public String cancelar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            pedidoService.cancelarPedido(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Pedido #" + id + " cancelado e estoque estornado!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao cancelar: " + e.getMessage());
        }
        return "redirect:/pedidos";
    }
}