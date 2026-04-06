package br.edu.iff.ccc.marketplacemultivendedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.edu.iff.ccc.marketplacemultivendedor.entity.Usuario;
import br.edu.iff.ccc.marketplacemultivendedor.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Listar todos os usuários
    @GetMapping
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarUsuarios();
    }

    // Buscar usuário por id
    @GetMapping("/{id}")
    public Usuario buscarPorId(@Valid @PathVariable Long id) {
        return usuarioService.buscarPorId(id);
    }

    // Criar usuário
    @PostMapping
    public Usuario criarUsuario(@Valid @RequestBody Usuario usuario) {
        return usuarioService.salvarUsuario(usuario);
    }

    // Atualizar usuário
    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@Valid @PathVariable Long id, @Valid @RequestBody Usuario usuario) {
        return usuarioService.atualizarUsuario(id, usuario);
    }

    // Deletar usuário
    @DeleteMapping("/{id}")
    public void deletarUsuario(@Valid @PathVariable Long id) {
        usuarioService.deletarUsuario(id);
    }
}