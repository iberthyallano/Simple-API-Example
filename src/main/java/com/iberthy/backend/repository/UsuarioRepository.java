package com.iberthy.backend.repository;

import com.iberthy.backend.model.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    @Query("select u from Usuario u where u.ativo = true")
    Page<Usuario> findAllActive(Pageable pageable);

    @Query("select u from Usuario u where u.id = :id and u.ativo = true")
    Usuario findByIdActive(Long id);

    @Query("select u from Usuario u where u.nome like concat('%', :nome, '%') and u.ativo = true")
    Usuario findByNomeContaining(String nome);

}
