package br.edu.iff.ccc.marketplacemultivendedor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.iff.ccc.marketplacemultivendedor.entity.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    
    List<Produto> findByNomeContaining(String nome);
    
}
