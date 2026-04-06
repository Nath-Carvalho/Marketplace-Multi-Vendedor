package br.edu.iff.ccc.marketplacemultivendedor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;

@Entity
public class ItemPedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemPedidoId;

    @Min(value = 1, message = "A quantidade deve ser no mínimo 1")
    protected int quantidade;
    
    @Min(value = 0, message = "O preço unitário não pode ser negativo")
    protected double precoUnitario;
    
    @Min(value = 0, message = "O subtotal não pode ser negativo")
    protected double subtotal;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Pedido pedido;

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(double precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double calcularSubtotal() {
        return subtotal;
    }

    public void enviarNotificacaoVendedor() {
    }
}
