package br.edu.iff.ccc.marketplacemultivendedor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.marketplacemultivendedor.entity.Carrinho;
import br.edu.iff.ccc.marketplacemultivendedor.repository.CarrinhoRepository;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    public List<Carrinho> listarCarrinhos() {
        return carrinhoRepository.findAll();
    }

    public Carrinho buscarPorId(Long id) {
        return carrinhoRepository.findById(id).orElse(null);
    }

    public Carrinho salvarCarrinho(Carrinho carrinho) {
        return carrinhoRepository.save(carrinho);
    }

    public Carrinho atualizarCarrinho(Long id, Carrinho carrinhoAtualizado) {
        Carrinho carrinho = carrinhoRepository.findById(id).orElse(null);

        if (carrinho != null) {
            return carrinhoRepository.save(carrinhoAtualizado);
        }

        return null;
    }

    public void deletarCarrinho(Long id) {
        carrinhoRepository.deleteById(id);
    }
}