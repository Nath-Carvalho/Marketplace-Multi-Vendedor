package br.com.marketplace.api.entities;

import jakarta.persistence.*;

@Entity
public class ItemCarrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int quantidade;
    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private Carrinho carrinho;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public ItemCarrinho() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public Carrinho getCarrinho() { return carrinho; }
    public void setCarrinho(Carrinho carrinho) { this.carrinho = carrinho; }
    public Produto getProduto() { return produto; }
    public void setProduto(Produto produto) { this.produto = produto; }
}