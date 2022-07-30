package com.iberthy.backend.service;

import com.iberthy.backend.controller.dto.RequestUsuarioDTO;
import com.iberthy.backend.domain.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {

    Page<Usuario> findAll(RequestUsuarioDTO filtro, Pageable pageable);

    Usuario findById(Long id);

    Usuario finByUsername(String username);

    Usuario save(RequestUsuarioDTO usuario);

    Usuario edite(Long id, RequestUsuarioDTO usuario);

    Usuario delete(Long id);

}
