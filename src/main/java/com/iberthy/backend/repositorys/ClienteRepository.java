package com.iberthy.backend.repositorys;

import com.iberthy.backend.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("select c from Cliente c where c.ativo = true")
    List<Cliente> findAllActive();

    @Query("select c from Cliente c where c.id = :id and c.ativo = true")
    Cliente findByIdActive(Long id);

    @Query("select c from Cliente c where c.nome like concat('%', :nome, '%') and c.ativo = true")
    Cliente findByNomeContaining(String nome);

}
