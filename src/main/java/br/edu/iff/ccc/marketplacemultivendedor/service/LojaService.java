package br.edu.iff.ccc.marketplacemultivendedor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.marketplacemultivendedor.entity.Loja;
import br.edu.iff.ccc.marketplacemultivendedor.repository.LojaRepository;

@Service
public class LojaService {

    @Autowired
    private LojaRepository lojaRepository;

    public List<Loja> listarLojas() {
        return lojaRepository.findAll();
    }

    public Loja buscarPorId(Long id) {
        return lojaRepository.findById(id).orElse(null);
    }

    public Loja salvarLoja(Loja loja) {
        return lojaRepository.save(loja);
    }

    public Loja atualizarLoja(Long id, Loja lojaAtualizada) {
        Loja loja = lojaRepository.findById(id).orElse(null);

        if (loja != null) {
            return lojaRepository.save(lojaAtualizada);
        }

        return null;
    }

    public void deletarLoja(Long id) {
        lojaRepository.deleteById(id);
    }
}