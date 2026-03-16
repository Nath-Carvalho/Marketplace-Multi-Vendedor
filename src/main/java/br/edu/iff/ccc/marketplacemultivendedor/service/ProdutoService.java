package br.edu.iff.ccc.marketplacemultivendedor.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.marketplacemultivendedor.dto.ProdutoRequest;
import br.edu.iff.ccc.marketplacemultivendedor.dto.ProdutoResponse;
import br.edu.iff.ccc.marketplacemultivendedor.entity.Produto;
import br.edu.iff.ccc.marketplacemultivendedor.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoResponse> listarProdutos() {

        return produtoRepository.findAll()
                .stream()
                .map(produto -> new ProdutoResponse(
                        produto.getProdutoId(),
                        produto.getNome(),
                        produto.getDescricao(),
                        produto.getPreco()))
                .collect(Collectors.toList());
    }

    public ProdutoResponse criarProduto(ProdutoRequest dto) {

        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());

        Produto salvo = produtoRepository.save(produto);

        return new ProdutoResponse(
                salvo.getProdutoId(),
                salvo.getNome(),
                salvo.getDescricao(),
                salvo.getPreco());
    }

    public List<ProdutoResponse> buscarPorNome(String nome) {
        return produtoRepository.findByNomeContaining(nome)
                .stream()
                .map(produto -> new ProdutoResponse(
                        produto.getProdutoId(),
                        produto.getNome(),
                        produto.getDescricao(),
                        produto.getPreco()))
                .collect(Collectors.toList());
    }

    public ProdutoResponse buscarPorId(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return new ProdutoResponse(
                produto.getProdutoId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco());
    }

    public ProdutoResponse atualizarProduto(Long id, ProdutoRequest dto) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());

        Produto atualizado = produtoRepository.save(produto);

        return new ProdutoResponse(
                atualizado.getProdutoId(),
                atualizado.getNome(),
                atualizado.getDescricao(),
                atualizado.getPreco());
    }

    public void deletarProduto(Long id) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produtoRepository.delete(produto);
    }



}