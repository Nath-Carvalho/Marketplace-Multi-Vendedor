package br.com.marketplace.api.controller;

import br.com.marketplace.api.dto.VendedorRequestDTO;
import br.com.marketplace.api.entities.Vendedor;
import br.com.marketplace.api.repository.VendedorRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/vendedores")
public class VendedorController {

    private final VendedorRepository repository;

    public VendedorController(VendedorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String listarTodos(Model model) {
        model.addAttribute("vendedores", repository.findAll());
        return "vendedores/lista";
    }

    @GetMapping("/novo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("vendedorDTO", new VendedorRequestDTO());
        return "vendedores/form";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicao(@PathVariable Integer id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Vendedor vendedorExistente = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));

            VendedorRequestDTO dtoForm = new VendedorRequestDTO();
            dtoForm.setNome(vendedorExistente.getNome());
            dtoForm.setEmail(vendedorExistente.getEmail());
            dtoForm.setSenha(vendedorExistente.getSenha());
            dtoForm.setNomeLoja(vendedorExistente.getNomeLoja());
            
            model.addAttribute("vendedorDTO", dtoForm);
            model.addAttribute("vendedorId", id); 
            return "vendedores/form";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Loja não encontrada.");
            return "redirect:/vendedores";
        }
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("vendedorDTO") VendedorRequestDTO request,
                            BindingResult result,
                            @RequestParam(required = false) Integer vendedorId, 
                            RedirectAttributes redirectAttributes) {
        
        if (result.hasErrors()) {
            return "vendedores/form";
        }

        try {
            Vendedor vendedor;
            
            if (vendedorId == null) {
                vendedor = new Vendedor();
                vendedor.setStatusAprovacao(true); 
            } else {
                vendedor = repository.findById(vendedorId)
                        .orElseThrow(() -> new RuntimeException("Vendedor não encontrado"));
            }

            vendedor.setNome(request.getNome());
            vendedor.setEmail(request.getEmail());
            vendedor.setSenha(request.getSenha());
            vendedor.setNomeLoja(request.getNomeLoja());

            repository.save(vendedor);

            String mensagem = (vendedorId == null) ? "Vendedor cadastrado com sucesso!" : "Vendedor atualizado com sucesso!";
            redirectAttributes.addFlashAttribute("mensagemSucesso", mensagem);
            return "redirect:/vendedores";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao salvar: " + e.getMessage());
            return "redirect:/vendedores";
        }
    }

    @GetMapping("/excluir/{id}")
    public String deletar(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        try {
            repository.deleteById(id);
            redirectAttributes.addFlashAttribute("mensagemSucesso", "Loja excluída com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("mensagemErro", "Erro ao excluir: Não é possível apagar uma loja que já possui produtos cadastrados.");
        }
        return "redirect:/vendedores";
    }
}