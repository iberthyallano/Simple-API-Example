package com.iberthy.backend.repository;

import com.iberthy.backend.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("select p from Produto p where p.id = :id and p.ativo = true")
    Produto findByIdActive(Long id);

}
