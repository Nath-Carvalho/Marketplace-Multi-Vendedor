package br.edu.iff.ccc.marketplacemultivendedor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.marketplacemultivendedor.entity.Usuario;
import br.edu.iff.ccc.marketplacemultivendedor.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario salvarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if (usuario != null) {
            return usuarioRepository.save(usuarioAtualizado);
        }

        return null;
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}