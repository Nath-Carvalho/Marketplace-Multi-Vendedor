package br.com.marketplace.api.controller;

import br.com.marketplace.api.dto.ProdutoRequestDTO;
import br.com.marketplace.api.dto.ProdutoResponseDTO;
import br.com.marketplace.api.repository.VendedorRepository;
import br.com.marketplace.api.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService service;
    private final VendedorRepository vendedorRepository;

    // Construtor atualizado para receber o VendedorRepository
    public ProdutoController(ProdutoService service, VendedorRepository vendedorRepository) {
        this.service = service;
        this.vendedorRepository = vendedorRepository;
    }

    @GetMapping
    public String listarTodos(Model model) {
        List<ProdutoResponseDTO> produtos = service.listarTodos();
        model.addAttribute("produtos", produtos);
        return "produtos/lista"; 
    }

    @GetMapping("/novo")
    public String mostrarFormularioCriacao(Model model) {
        model.addAttribute("produtoDTO", new ProdutoRequestDTO());
        // Busca os vendedores no banco para popular o <select>
        model.addAttribute("vendedores", vendedorRepository.findAll()); 
        return "produtos/form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            ProdutoResponseDTO produtoExistente = service.buscarPorId(id);
            ProdutoRequestDTO dtoForm = new ProdutoRequestDTO();
            dtoForm.setNome(produtoExistente.getNome());
            dtoForm.setDescricao(produtoExistente.getDescricao());
            dtoForm.setPreco(produtoExistente.getPreco());
            dtoForm.setEstoque(produtoExistente.getEstoque());
            
            model.addAttribute("produtoDTO", dtoForm);
            model.addAttribute("produtoId", id);
            model.addAttribute("vendedores", vendedorRepository.findAll()); 
            return "produtos/form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Produto não encontrado.");
            return "redirect:/produtos";
        }
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("produtoDTO") ProdutoRequestDTO request,
                         BindingResult result,
                         @RequestParam(required = false) Integer produtoId, 
                         RedirectAttributes redirectAttributes,
                         Model model) { // Adicionamos o Model aqui
        
        if (result.hasErrors()) {
            model.addAttribute("vendedores", vendedorRepository.findAll());
            return "produtos/form"; 
        }

        if (produtoId == null) {
            service.criar(request);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Produto criado com sucesso!");
        } else {
            service.atualizar(produtoId, request);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Produto atualizado com sucesso!");
        }

        return "redirect:/produtos";
    }

    @GetMapping("/excluir/{id}")
    public String deletar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            service.deletar(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Produto excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao excluir: " + e.getMessage());
        }
        return "redirect:/produtos";
    }
}