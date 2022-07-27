package com.iberthy.backend.service;

import com.iberthy.backend.domain.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {

    Page<Usuario> findAll(Usuario filtro, Pageable pageable);

    Usuario findById(Long id);

    Usuario save(Usuario usuario);

    Usuario edite(Long id, Usuario usuario);

    Usuario delete(Long id);

}
