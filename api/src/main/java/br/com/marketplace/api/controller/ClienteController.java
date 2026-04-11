package br.com.marketplace.api.controller;

import br.com.marketplace.api.dto.ClienteRequestDTO;
import br.com.marketplace.api.entities.Cliente;
import br.com.marketplace.api.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteRepository repository;

    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("clientes", repository.findAll());
        return "clientes/lista";
    }

    @GetMapping("/novo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("clienteDTO", new ClienteRequestDTO());
        return "clientes/form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Cliente clienteExistente = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
            
            ClienteRequestDTO dtoForm = new ClienteRequestDTO();
            dtoForm.setNome(clienteExistente.getNome());
            dtoForm.setEmail(clienteExistente.getEmail());
            dtoForm.setSenha(clienteExistente.getSenha());
            dtoForm.setEndereco(clienteExistente.getEndereco());
            
            model.addAttribute("clienteDTO", dtoForm);
            model.addAttribute("clienteId", id); 
            return "clientes/form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Cliente não encontrado.");
            return "redirect:/clientes";
        }
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("clienteDTO") ClienteRequestDTO request,
                         BindingResult result,
                         @RequestParam(required = false) Integer clienteId,
                         RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            return "clientes/form";
        }

        try {
            Cliente cliente;
            if (clienteId == null) {
                cliente = new Cliente(); 
            } else {
                cliente = repository.findById(clienteId)
                        .orElseThrow(() -> new RuntimeException("Cliente não encontrado")); 
            }

            cliente.setNome(request.getNome());
            cliente.setEmail(request.getEmail());
            cliente.setSenha(request.getSenha());
            cliente.setEndereco(request.getEndereco());

            repository.save(cliente);

            String msg = (clienteId == null) ? "Cliente cadastrado com sucesso!" : "Cliente atualizado com sucesso!";
            redirectAttributes.addFlashAttribute("mensagemSucesso", msg);
            return "redirect:/clientes";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao salvar: " + e.getMessage());
            return "redirect:/clientes";
        }
    }

    @GetMapping("/excluir/{id}")
    public String deletar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            repository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Cliente excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro: Não é possível apagar um cliente que já possui pedidos no sistema.");
        }
        return "redirect:/clientes";
    }
}