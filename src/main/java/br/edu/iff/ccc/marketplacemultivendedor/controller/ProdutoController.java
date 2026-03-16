package br.edu.iff.ccc.marketplacemultivendedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.edu.iff.ccc.marketplacemultivendedor.dto.ProdutoRequest;
import br.edu.iff.ccc.marketplacemultivendedor.dto.ProdutoResponse;
import br.edu.iff.ccc.marketplacemultivendedor.service.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Listar todos os produtos
    @GetMapping("/produtos")
    public List<ProdutoResponse> listarProdutos() {
    return produtoService.listarProdutos();
}

    // Buscar produto por nome
    @GetMapping("/produtos/nome/{nome}")
    public List<ProdutoResponse> buscarPorNome(@Valid @PathVariable String nome) {
    return produtoService.buscarPorNome(nome);
}

    // Buscar produto por id
    @GetMapping("/{id}")
    public ProdutoResponse buscarPorId(@Valid @PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    // Criar produto
    @PostMapping
    public ProdutoResponse criarProduto(@Valid @RequestBody ProdutoRequest dto) {
        return produtoService.criarProduto(dto);
    }

    // Atualizar produto
    @PutMapping("/{id}")
    public ProdutoResponse atualizarProduto(@Valid @PathVariable Long id, @Valid @RequestBody ProdutoRequest dto) {
        return produtoService.atualizarProduto(id, dto);
    }

    // Deletar produto
    @DeleteMapping("/{id}")
    public void deletarProduto(@Valid @PathVariable Long id) {
        produtoService.deletarProduto(id);
    }
}