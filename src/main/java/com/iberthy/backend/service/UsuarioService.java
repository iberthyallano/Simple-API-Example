package com.iberthy.backend.service;

import com.iberthy.backend.controller.dto.request.RequestUsuarioDTO;
import com.iberthy.backend.domain.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsuarioService extends UserDetailsService {

    Page<Usuario> findAll(Usuario filtro, Pageable pageable);

    Usuario findById(Long id);

    Usuario finByUsername(String username);

    Usuario save(Usuario usuario);

    Usuario edite(Long id, Usuario usuario);

    Usuario delete(Long id);

}
