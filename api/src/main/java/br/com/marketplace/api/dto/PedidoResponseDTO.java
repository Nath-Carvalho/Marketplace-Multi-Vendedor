package br.com.marketplace.api.dto;

import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponseDTO {
    private Integer pedidoId;
    private LocalDateTime data;
    private String status;
    private List<ItemPedidoResponseDTO> itens;

    public PedidoResponseDTO() {}

    public Integer getPedidoId() { return pedidoId; }
    public void setPedidoId(Integer pedidoId) { this.pedidoId = pedidoId; }
    public LocalDateTime getData() { return data; }
    public void setData(LocalDateTime data) { this.data = data; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public List<ItemPedidoResponseDTO> getItens() { return itens; }
    public void setItens(List<ItemPedidoResponseDTO> itens) { this.itens = itens; }
}