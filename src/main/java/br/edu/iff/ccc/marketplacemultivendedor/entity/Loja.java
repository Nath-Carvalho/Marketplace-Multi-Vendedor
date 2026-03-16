package br.edu.iff.ccc.marketplacemultivendedor.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Loja {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long lojaId;
    
    @NotBlank(message = "O nome da loja é obrigatório")
    @Size(min = 3, max = 100, message = "O nome da loja deve ter entre 3 e 100 caracteres")
    protected String nome;
    
    @NotBlank(message = "A descrição é obrigatória")
    @Size(max = 255, message = "A descrição não pode exceder 255 caracteres", min = 5)
    protected String descricao;
   
    protected Long vendedorId;

    @OneToMany(mappedBy = "loja")
    private List<Produto> produtos;

    public Long getVendedorId() {
        return vendedorId;
    }


    public void setVendedorId(Long vendedorId) {
        this.vendedorId = vendedorId;
    }


    public String getDescricao() {
        return descricao;
    }


    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public Long getLojaId() {
        return lojaId;
    }


    public void setLojaId(Long lojaId) {
        this.lojaId = lojaId;
    }


    public void gerenciarProdutos() {
    }
}
