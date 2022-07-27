package com.iberthy.backend.repository;

import com.iberthy.backend.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("select c from Cliente c where c.ativo = true")
    Page<Cliente> findAllActive(Pageable pageable);

    @Query("select c from Cliente c where c.id = :id and c.ativo = true")
    Cliente findByIdActive(Long id);

}
