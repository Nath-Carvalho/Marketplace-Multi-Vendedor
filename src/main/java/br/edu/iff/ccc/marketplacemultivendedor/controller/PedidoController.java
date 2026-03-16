package br.edu.iff.ccc.marketplacemultivendedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.edu.iff.ccc.marketplacemultivendedor.entity.Pedido;
import br.edu.iff.ccc.marketplacemultivendedor.service.PedidoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Listar todos os pedidos
    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoService.listarPedidos();
    }

    // Buscar pedido por id
    @GetMapping("/{id}")
    public Pedido buscarPorId(@Valid @PathVariable Long id) {
        return pedidoService.buscarPorId(id);
    }

    // Criar pedido
    @PostMapping
    public Pedido criarPedido(@Valid @RequestBody Pedido pedido) {
        return pedidoService.salvarPedido(pedido);
    }

    // Atualizar pedido
    @PutMapping("/{id}")
    public Pedido atualizarPedido(@Valid @PathVariable Long id, @Valid @RequestBody Pedido pedido) {
        return pedidoService.atualizarPedido(id, pedido);
    }

    // Deletar pedido
    @DeleteMapping("/{id}")
    public void deletarPedido(@Valid @PathVariable Long id) {
        pedidoService.deletarPedido(id);
    }
}