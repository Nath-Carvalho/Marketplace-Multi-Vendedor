package br.com.marketplace.api.service;

import br.com.marketplace.api.dto.ProdutoRequestDTO;
import br.com.marketplace.api.dto.ProdutoResponseDTO;
import br.com.marketplace.api.entities.Produto;
import br.com.marketplace.api.repository.ProdutoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public List<ProdutoResponseDTO> listarTodos() {
        return repository.findAll().stream().map(this::converterParaDTO).collect(Collectors.toList());
    }

    public ProdutoResponseDTO buscarPorId(Integer id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        return converterParaDTO(produto);
    }

    @Transactional
    public ProdutoResponseDTO criar(ProdutoRequestDTO dto) {
        Produto produto = new Produto();
        BeanUtils.copyProperties(dto, produto);
        Produto salvo = repository.save(produto);
        return converterParaDTO(salvo);
    }

    @Transactional
    public ProdutoResponseDTO atualizar(Integer id, ProdutoRequestDTO dto) {
        Produto produtoExistente = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
        
        BeanUtils.copyProperties(dto, produtoExistente, "produtoId");
        Produto salvo = repository.save(produtoExistente);
        return converterParaDTO(salvo);
    }

    @Transactional
    public void deletar(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }
        repository.deleteById(id);
    }

    private ProdutoResponseDTO converterParaDTO(Produto produto) {
        ProdutoResponseDTO dto = new ProdutoResponseDTO();
        BeanUtils.copyProperties(produto, dto);
        return dto;
    }
}