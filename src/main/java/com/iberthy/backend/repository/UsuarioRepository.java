package com.iberthy.backend.repository;

import com.iberthy.backend.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    @Query("select u from Usuario u where u.id = :id and u.ativo = true")
    Usuario findByIdActive(Long id);

}
