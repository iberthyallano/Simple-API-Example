package com.iberthy.backend.service;

import com.iberthy.backend.model.Usuario;
import com.iberthy.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<Usuario> findAll(Pageable pageable){
        return usuarioRepository.findAllActive(pageable);
    }

    public Usuario findById(Long id){
        return usuarioRepository.findByIdActive(id);
    }

    public Usuario findByNome(String nome){
        return usuarioRepository.findByNomeContaining(nome);
    }

    @Transactional
    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario edite(Long id, Usuario usuario){
        var usuarioDb = usuarioRepository.findByIdActive(id);

        if(usuarioDb != null){
            usuario.setId(usuarioDb.getId());
            return this.save(usuario);
        }

        return null;
    }

    @Transactional
    public Usuario delete(Long id){
        var usuarioDb = usuarioRepository.findByIdActive(id);

        if(usuarioDb != null){
            usuarioDb.setAtivo(false);
            return usuarioRepository.save(usuarioDb);
        }

        return null;
    }

}
