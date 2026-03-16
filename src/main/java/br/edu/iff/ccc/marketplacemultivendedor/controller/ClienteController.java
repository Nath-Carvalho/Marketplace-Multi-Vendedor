package br.edu.iff.ccc.marketplacemultivendedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.edu.iff.ccc.marketplacemultivendedor.entity.Cliente;
import br.edu.iff.ccc.marketplacemultivendedor.service.ClienteService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Listar todos os clientes
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    // Buscar cliente por id
    @GetMapping("/{id}")
    public Cliente buscarPorId(@Valid @PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    // Criar cliente
    @PostMapping
    public Cliente criarCliente(@Valid @RequestBody Cliente cliente) {
        return clienteService.salvarCliente(cliente);
    }

    // Atualizar cliente
    @PutMapping("/{id}")
    public Cliente atualizarCliente(@Valid @PathVariable Long id, @Valid @RequestBody Cliente cliente) {
        return clienteService.atualizarCliente(id, cliente);
    }

    // Deletar cliente
    @DeleteMapping("/{id}")
    public void deletarCliente(@Valid @PathVariable Long id) {
        clienteService.deletarCliente(id);
    }
}
