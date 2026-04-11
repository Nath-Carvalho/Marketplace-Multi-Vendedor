package br.com.marketplace.api.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Cliente extends Usuario {
    
    private String endereco;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;

    public Cliente() {}

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    public List<Pedido> getPedidos() { return pedidos; }
    public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }
}