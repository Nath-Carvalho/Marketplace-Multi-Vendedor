package br.com.marketplace.api.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Loja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lojaId;
    private String nome;
    private String descricao;
    private Integer vendedorId;

    @OneToMany(mappedBy = "vendedorId") 
    private List<Produto> produtos;

    public Loja() {}

    public Integer getLojaId() { return lojaId; }
    public void setLojaId(Integer lojaId) { this.lojaId = lojaId; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Integer getVendedorId() { return vendedorId; }
    public void setVendedorId(Integer vendedorId) { this.vendedorId = vendedorId; }
    public List<Produto> getProdutos() { return produtos; }
    public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }
}