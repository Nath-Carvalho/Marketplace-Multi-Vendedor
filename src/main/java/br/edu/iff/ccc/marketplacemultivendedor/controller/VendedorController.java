package br.edu.iff.ccc.marketplacemultivendedor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import br.edu.iff.ccc.marketplacemultivendedor.entity.Vendedor;
import br.edu.iff.ccc.marketplacemultivendedor.service.VendedorService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    // Listar todos os vendedores
    @GetMapping
    public List<Vendedor> listarVendedores() {
        return vendedorService.listarVendedores();
    }

    // Buscar vendedor por id
    @GetMapping("/{id}")
    public Vendedor buscarPorId(@Valid @PathVariable Long id) {
        return vendedorService.buscarPorId(id);
    }

    // Criar vendedor
    @PostMapping
    public Vendedor criarVendedor(@Valid @RequestBody Vendedor vendedor) {
        return vendedorService.salvarVendedor(vendedor);
    }

    // Atualizar vendedor
    @PutMapping("/{id}")
    public Vendedor atualizarVendedor(@Valid @PathVariable Long id, @Valid @RequestBody Vendedor vendedor) {
        return vendedorService.atualizarVendedor(id, vendedor);
    }

    // Deletar vendedor
    @DeleteMapping("/{id}")
    public void deletarVendedor(@Valid @PathVariable Long id) {
        vendedorService.deletarVendedor(id);
    }
}