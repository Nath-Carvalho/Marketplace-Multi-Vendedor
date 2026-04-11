package br.com.marketplace.api.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ItemPedidoRequestDTO {

    @NotNull(message = "O ID do produto é obrigatório")
    private Integer produtoId;

    @Min(value = 1, message = "A quantidade deve ser de pelo menos 1")
    private int quantidade;

    public ItemPedidoRequestDTO() {}

    public Integer getProdutoId() { return produtoId; }
    public void setProdutoId(Integer produtoId) { this.produtoId = produtoId; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
}