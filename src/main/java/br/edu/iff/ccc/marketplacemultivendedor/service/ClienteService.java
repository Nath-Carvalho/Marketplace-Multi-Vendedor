package br.edu.iff.ccc.marketplacemultivendedor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.iff.ccc.marketplacemultivendedor.entity.Cliente;
import br.edu.iff.ccc.marketplacemultivendedor.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        Cliente cliente = clienteRepository.findById(id).orElse(null);

        if (cliente != null) {
            return clienteRepository.save(clienteAtualizado);
        }

        return null;
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }
}