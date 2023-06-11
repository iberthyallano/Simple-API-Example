package com.iberthy.backend.service;

import com.iberthy.backend.domain.entity.UsuarioModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UsuarioService extends UserDetailsService {

    List<UsuarioModel> findAll(UsuarioModel filtro);

    UsuarioModel finByUsername(String username);

    UsuarioModel save(UsuarioModel usuario);

    Boolean updatePassword(Long id, String password);

    UsuarioModel edite(Long id, UsuarioModel usuario);

    Boolean delete(Long id);
}
