package br.edu.iff.ccc.marketplacemultivendedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.edu.iff.ccc.marketplacemultivendedor.entity.Carrinho;
import br.edu.iff.ccc.marketplacemultivendedor.service.CarrinhoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/carrinhos")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    // Listar todos os carrinhos
    @GetMapping
    public List<Carrinho> listarCarrinhos() {
        return carrinhoService.listarCarrinhos();
    }

    // Buscar carrinho por id
    @GetMapping("/{id}")
    public Carrinho buscarPorId(@Valid @PathVariable Long id) {
        return carrinhoService.buscarPorId(id);
    }

    // Criar carrinho
    @PostMapping
    public Carrinho criarCarrinho(@Valid @RequestBody Carrinho carrinho) {
        return carrinhoService.salvarCarrinho(carrinho);
    }

    // Atualizar carrinho
    @PutMapping("/{id}")
    public Carrinho atualizarCarrinho(@Valid @PathVariable Long id, @Valid @RequestBody Carrinho carrinho) {
        return carrinhoService.atualizarCarrinho(id, carrinho);
    }

    // Deletar carrinho
    @DeleteMapping("/{id}")
    public void deletarCarrinho(@Valid @PathVariable Long id) {
        carrinhoService.deletarCarrinho(id);
    }
}