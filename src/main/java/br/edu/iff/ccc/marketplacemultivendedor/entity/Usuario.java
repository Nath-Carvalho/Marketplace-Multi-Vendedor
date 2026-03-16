package br.edu.iff.ccc.marketplacemultivendedor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
//Ela diz ao JPA como salvar essa herança no banco de dados. 
// Com InheritanceType.JOINED, o banco cria uma tabela para cada classe.
@Inheritance(strategy = InheritanceType.JOINED) 
 public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @NotBlank(message = "O nome não pode estar vazio")
    @Size(min = 3, max = 100, message = "")
    protected String nome;

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    protected String email;

    @NotBlank(message = "A senha é obrigatória")
    @Size(min = 8, max = 100, message = "A senha deve ter no mínimo 8 caracteres")
    protected String senha;
    

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void autenticar() {
    }

    public void logout() {
    }
}
