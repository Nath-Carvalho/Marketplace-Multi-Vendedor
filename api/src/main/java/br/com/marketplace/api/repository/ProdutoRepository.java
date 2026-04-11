package br.com.marketplace.api.repository;

import br.com.marketplace.api.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findByNomeContainingIgnoreCase(String nome);

    @Query("SELECT p FROM Produto p WHERE p.preco <= :valorMaximo AND p.estoque > 0")
    List<Produto> buscarDisponiveisAbaixoDe(@Param("valorMaximo") double valorMaximo);
}