package br.com.marketplace.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClienteRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 50)
    private String nome;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "O formato do email é inválido") 
    private String email;

    @NotBlank(message = "A senha não pode estar vazia")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;

    private String endereco;

    public ClienteRequestDTO() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
}