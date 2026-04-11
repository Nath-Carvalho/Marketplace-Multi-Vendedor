package br.com.marketplace.api.service;

import br.com.marketplace.api.dto.*;
import br.com.marketplace.api.entities.*;
import br.com.marketplace.api.repository.PedidoRepository;
import br.com.marketplace.api.repository.ProdutoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional
    public PedidoResponseDTO criarPedido(PedidoRequestDTO dto) {
        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.now());
        pedido.setStatus("PENDENTE");

        Cliente cliente = new Cliente();
        cliente.setId(dto.getClienteId());
        pedido.setCliente(cliente);

        List<ItemPedido> itensEntidade = new ArrayList<>();

        for (ItemPedidoRequestDTO itemDto : dto.getItens()) {
            Produto produto = produtoRepository.findById(itemDto.getProdutoId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));

            if (produto.getEstoque() < itemDto.getQuantidade()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estoque insuficiente para o produto: " + produto.getNome());
            }

            produto.setEstoque(produto.getEstoque() - itemDto.getQuantidade());
            produtoRepository.save(produto);

            ItemPedido item = new ItemPedido();
            item.setProduto(produto);
            item.setPedido(pedido);
            item.setQuantidade(itemDto.getQuantidade());
            item.setPrecoUnitario(produto.getPreco());
            item.setSubtotal(produto.getPreco() * itemDto.getQuantidade());

            itensEntidade.add(item);
        }

        pedido.setItens(itensEntidade);
        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        return converterParaResponseDTO(pedidoSalvo);
    }

    @Transactional
    public void cancelarPedido(Integer id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        if ("CANCELADO".equals(pedido.getStatus())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Este pedido já está cancelado.");
        }

        for (ItemPedido item : pedido.getItens()) {
            Produto produto = item.getProduto();
            produto.setEstoque(produto.getEstoque() + item.getQuantidade());
            produtoRepository.save(produto);
        }

        pedido.setStatus("CANCELADO");
        pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Integer id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }

    private PedidoResponseDTO converterParaResponseDTO(Pedido pedido) {
        PedidoResponseDTO response = new PedidoResponseDTO();
        response.setPedidoId(pedido.getPedidoId());
        response.setData(pedido.getData());
        response.setStatus(pedido.getStatus());

        List<ItemPedidoResponseDTO> itensDTO = pedido.getItens().stream().map(item -> {
            ItemPedidoResponseDTO itemDTO = new ItemPedidoResponseDTO();
            itemDTO.setNomeProduto(item.getProduto().getNome());
            itemDTO.setQuantidade(item.getQuantidade());
            itemDTO.setPrecoUnitario(item.getPrecoUnitario());
            itemDTO.setSubtotal(item.getSubtotal());
            return itemDTO;
        }).collect(Collectors.toList());

        response.setItens(itensDTO);
        return response;
    }
}