package com.iberthy.backend.repository;

import com.iberthy.backend.domain.entity.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long>{

    @Query("select u from UsuarioModel u where u.id = :id and u.enabled = true")
    UsuarioModel findByIdActive(Long id);

    @Query("select u from UsuarioModel u where u.username = :username and u.enabled = true")
    UsuarioModel findByUsernameActive(String username);

}
