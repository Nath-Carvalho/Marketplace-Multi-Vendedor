package br.com.marketplace.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainViewController {

    @GetMapping("/")
    public String paginaInicial() {
        // Agora, em vez do redirect, ele retorna o nome do arquivo "index.html"
        return "index"; 
    }
}