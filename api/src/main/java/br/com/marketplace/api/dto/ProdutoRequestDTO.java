package br.com.marketplace.api.dto;

import jakarta.validation.constraints.*;

public class ProdutoRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100)
    private String nome;

    @NotBlank(message = "A descrição não pode estar vazia")
    private String descricao;

    @Min(value = 1, message = "O preço deve ser maior que zero")
    private double preco;

    @Min(value = 0, message = "O estoque não pode ser negativo")
    private int estoque;
    
    @NotNull(message = "O ID do vendedor é obrigatório")
    private Integer vendedorId;

    public ProdutoRequestDTO() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public int getEstoque() { return estoque; }
    public void setEstoque(int estoque) { this.estoque = estoque; }
    public Integer getVendedorId() { return vendedorId; }
    public void setVendedorId(Integer vendedorId) { this.vendedorId = vendedorId; }
}