package br.edu.iff.ccc.marketplacemultivendedor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.edu.iff.ccc.marketplacemultivendedor.entity.Adm;

@Repository
public interface AdmRepository extends JpaRepository<Adm, Long>{

}
