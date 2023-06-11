package com.iberthy.backend.repository;

import com.iberthy.backend.domain.entity.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

    @Query("select p from ProdutoModel p where p.id = :id and p.enabled = true")
    ProdutoModel findByIdActive(Long id);

}
