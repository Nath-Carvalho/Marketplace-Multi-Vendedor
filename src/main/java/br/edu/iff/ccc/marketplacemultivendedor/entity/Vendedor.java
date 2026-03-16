package br.edu.iff.ccc.marketplacemultivendedor.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Vendedor extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long vendedorId;
    
    protected String nomeLoja;
    protected boolean statusAprovacao;

    @OneToMany(mappedBy = "vendedor")
    private List<Loja> lojas;
    
    public boolean isStatusAprovacao() {
        return statusAprovacao;
    }

    public void setStatusAprovacao(boolean statusAprovacao) {
        this.statusAprovacao = statusAprovacao;
    }

    public String getNomeLoja() {
        return nomeLoja;
    }

    public void setNomeLoja(String nomeLoja) {
        this.nomeLoja = nomeLoja;
    }

    public Long getVendedorId() {
        return vendedorId;
    }

    public void setVendedorId(Long vendedorId) {
        this.vendedorId = vendedorId;
    }

    public void cadastrarProduto(Produto produto) {
    }

    public void editarProduto(Produto produto) {
    }

    public void removerProduto(Long produtoId) {
    }

    public void receberNotificacao(Pedido pedido) {
    }
}
