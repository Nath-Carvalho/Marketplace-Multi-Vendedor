package br.edu.iff.ccc.marketplacemultivendedor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.marketplacemultivendedor.entity.Pedido;
import br.edu.iff.ccc.marketplacemultivendedor.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public Pedido salvarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido atualizarPedido(Long id, Pedido pedidoAtualizado) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);

        if (pedido != null) {
            return pedidoRepository.save(pedidoAtualizado);
        }

        return null;
    }

    public void deletarPedido(Long id) {
        pedidoRepository.deleteById(id);
    }
}