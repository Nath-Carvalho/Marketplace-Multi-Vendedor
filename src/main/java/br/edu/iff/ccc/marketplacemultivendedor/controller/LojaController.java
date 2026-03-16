package br.edu.iff.ccc.marketplacemultivendedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.edu.iff.ccc.marketplacemultivendedor.entity.Loja;
import br.edu.iff.ccc.marketplacemultivendedor.service.LojaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/lojas")
public class LojaController {

    @Autowired
    private LojaService lojaService;

    // Listar todas as lojas
    @GetMapping
    public List<Loja> listarLojas() {
        return lojaService.listarLojas();
    }

    // Buscar loja por id
    @GetMapping("/{id}")
    public Loja buscarPorId(@Valid @PathVariable Long id) {
        return lojaService.buscarPorId(id);
    }

    // Criar loja
    @PostMapping
    public Loja criarLoja(@Valid @RequestBody Loja loja) {
        return lojaService.salvarLoja(loja);
    }

    // Atualizar loja
    @PutMapping("/{id}")
    public Loja atualizarLoja(@Valid @PathVariable Long id, @Valid @RequestBody Loja loja) {
        return lojaService.atualizarLoja(id, loja);
    }

    // Deletar loja
    @DeleteMapping("/{id}")
    public void deletarLoja(@Valid @PathVariable Long id) {
        lojaService.deletarLoja(id);
    }
}
