package com.iberthy.backend.repository;

import com.iberthy.backend.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("select c from Cliente c where c.id = :id and c.ativo = true")
    Cliente findByIdActive(Long id);

}
