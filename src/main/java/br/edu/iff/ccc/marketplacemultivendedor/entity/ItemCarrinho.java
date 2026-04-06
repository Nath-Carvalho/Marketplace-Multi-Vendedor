package br.edu.iff.ccc.marketplacemultivendedor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;

@Entity
public class ItemCarrinho {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemCarrinhoId;

    @Min(value = 1, message = "Quantidade mínima é 1")
    protected int quantidade;
    
    @Min(value = 0, message = "O subtotal não pode ser negativo")
    protected double subtotal;

    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Carrinho carrinho;
    
    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
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
}
