package br.edu.iff.ccc.marketplacemultivendedor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.marketplacemultivendedor.entity.Adm;
import br.edu.iff.ccc.marketplacemultivendedor.repository.AdmRepository;

@Service
public class AdmService {

    @Autowired
    private AdmRepository admRepository;

    public List<Adm> listarAdms() {
        return admRepository.findAll();
    }

    public Adm buscarPorId(Long id) {
        return admRepository.findById(id).orElse(null);
    }

    public Adm salvarAdm(Adm adm) {
        return admRepository.save(adm);
    }

    public Adm atualizarAdm(Long id, Adm admAtualizado) {
        Adm adm = admRepository.findById(id).orElse(null);

        if (adm != null) {
            adm.setNome(admAtualizado.getNome());
            return admRepository.save(adm);
        }

        return null;
    }

    public void deletarAdm(Long id) {
        admRepository.deleteById(id);
    }
}