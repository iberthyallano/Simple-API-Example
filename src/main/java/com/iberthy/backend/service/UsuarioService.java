package com.iberthy.backend.service;

import com.iberthy.backend.controller.dto.RequestUsuarioDTO;
import com.iberthy.backend.domain.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioService {

    Page<Usuario> findAll(RequestUsuarioDTO filtro, Pageable pageable);

    Usuario findById(Long id);

    Usuario save(RequestUsuarioDTO usuario);

    Usuario edite(Long id, RequestUsuarioDTO usuario);

    Usuario delete(Long id);

}
