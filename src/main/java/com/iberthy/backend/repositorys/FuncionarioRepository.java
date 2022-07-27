package com.iberthy.backend.repositorys;

import com.iberthy.backend.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

    @Query("select f from Funcionario f where f.ativo = true")
    List<Funcionario> findAllActive();

    @Query("select f from Funcionario f where f.id = :id and f.ativo = true")
    Funcionario findByIdActive(Long id);

    @Query("select f from Funcionario f where f.nome like concat('%', :nome, '%') and f.ativo = true")
    Funcionario findByNomeContaining(String nome);

}
