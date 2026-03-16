package br.edu.iff.ccc.marketplacemultivendedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.edu.iff.ccc.marketplacemultivendedor.entity.Adm;
import br.edu.iff.ccc.marketplacemultivendedor.service.AdmService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/adms")
public class AdmController {

    @Autowired
    private AdmService admService;

    // Listar todos os administradores
    @GetMapping
    public List<Adm> listarAdms() {
        return admService.listarAdms();
    }

    // Buscar administrador por id
    @GetMapping("/{id}")
    public Adm buscarPorId(@Valid @PathVariable Long id) {
        return admService.buscarPorId(id);
    }

    // Criar administrador
    @PostMapping
    public Adm criarAdm(@Valid @RequestBody Adm adm) {
        return admService.salvarAdm(adm);
    }

    // Atualizar administrador
    @PutMapping("/{id}")
    public Adm atualizarAdm(@Valid @PathVariable Long id, @RequestBody Adm adm) {
        return admService.atualizarAdm(id, adm);
    }

    // Deletar administrador
    @DeleteMapping("/{id}")
    public void deletarAdm(@Valid @PathVariable Long id) {
        admService.deletarAdm(id);
    }
}