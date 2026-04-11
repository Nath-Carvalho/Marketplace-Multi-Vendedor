package br.com.marketplace.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

public class PedidoRequestDTO {

    @NotNull(message = "O ID do cliente é obrigatório")
    private Integer clienteId;

    @NotEmpty(message = "O pedido deve conter pelo menos um item")
    @Valid 
    private List<ItemPedidoRequestDTO> itens;

    public PedidoRequestDTO() {}

    public Integer getClienteId() { return clienteId; }
    public void setClienteId(Integer clienteId) { this.clienteId = clienteId; }
    public List<ItemPedidoRequestDTO> getItens() { return itens; }
    public void setItens(List<ItemPedidoRequestDTO> itens) { this.itens = itens; }
}