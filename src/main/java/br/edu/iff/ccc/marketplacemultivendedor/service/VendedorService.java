package br.edu.iff.ccc.marketplacemultivendedor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.marketplacemultivendedor.entity.Vendedor;
import br.edu.iff.ccc.marketplacemultivendedor.repository.VendedorRepository;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    public List<Vendedor> listarVendedores() {
        return vendedorRepository.findAll();
    }

    public Vendedor buscarPorId(Long id) {
        return vendedorRepository.findById(id).orElse(null);
    }

    public Vendedor salvarVendedor(Vendedor vendedor) {
        return vendedorRepository.save(vendedor);
    }

    public Vendedor atualizarVendedor(Long id, Vendedor vendedorAtualizado) {
        Vendedor vendedor = vendedorRepository.findById(id).orElse(null);

        if (vendedor != null) {
            return vendedorRepository.save(vendedorAtualizado);
        }

        return null;
    }

    public void deletarVendedor(Long id) {
        vendedorRepository.deleteById(id);
    }
}