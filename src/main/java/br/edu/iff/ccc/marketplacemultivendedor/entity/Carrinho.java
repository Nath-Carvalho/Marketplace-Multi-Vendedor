package br.edu.iff.ccc.marketplacemultivendedor.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long carrinhoId;
    protected Long clienteId;
    protected String status;
    
    @OneToOne
    private Carrinho carrinho;

    @OneToOne
    private Cliente cliente;

    @OneToMany(mappedBy = "carrinho")
    private List<ItemCarrinho> itens;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getCarrinhoId() {
        return carrinhoId;
    }

    public void setCarrinhoId(Long carrinhoId) {
        this.carrinhoId = carrinhoId;
    }

     @ManyToMany
    private List<Produto> produtos;

    public void adicionarProduto(Produto produto, int quantidade) {
    }

    public void removerProduto(int produtoId) {
    }

    public double calcularTotal() {
        return 0;
    }

    public void limparCarrinho() {
    }
}