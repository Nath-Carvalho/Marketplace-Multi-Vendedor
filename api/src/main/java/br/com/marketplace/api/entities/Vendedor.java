package br.com.marketplace.api.entities;

import jakarta.persistence.Entity;

@Entity
public class Vendedor extends Usuario {
    
    private String nomeLoja;
    private boolean statusAprovacao;

    public Vendedor() {}

    public String getNomeLoja() { return nomeLoja; }
    public void setNomeLoja(String nomeLoja) { this.nomeLoja = nomeLoja; }
    public boolean isStatusAprovacao() { return statusAprovacao; }
    public void setStatusAprovacao(boolean statusAprovacao) { this.statusAprovacao = statusAprovacao; }
}