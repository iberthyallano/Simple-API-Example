package com.iberthy.backend.repository;

import com.iberthy.backend.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select r from Role r where r.nome = :nome")
    Role findByNome(String nome);

}
