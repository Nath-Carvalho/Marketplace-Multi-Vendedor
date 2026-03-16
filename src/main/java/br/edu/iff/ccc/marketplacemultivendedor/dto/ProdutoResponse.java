package br.edu.iff.ccc.marketplacemultivendedor.dto;

public class ProdutoResponse {

    private Long id;
    private String nome;
    private String descricao;
    private double preco;

    public ProdutoResponse(Long id, String nome, String descricao, double preco) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }
}