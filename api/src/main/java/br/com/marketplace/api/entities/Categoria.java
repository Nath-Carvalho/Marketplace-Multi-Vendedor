package br.com.marketplace.api.entities;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @ManyToMany(mappedBy = "categorias")
    private Set<Produto> produtos;

    public Categoria() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public Set<Produto> getProdutos() { return produtos; }
    public void setProdutos(Set<Produto> produtos) { this.produtos = produtos; }
}