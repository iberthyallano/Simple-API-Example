package com.iberthy.backend.service;

import com.iberthy.backend.model.Usuario;
import com.iberthy.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Page<Usuario> findAll(Usuario filtro, Pageable pageable){

        var matcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(filtro,matcher);

        if(filtro.getAtivo() != false){
            filtro.setAtivo(true);
        }

        return usuarioRepository.findAll(example, pageable);
    }

    public Usuario findById(Long id){
        return usuarioRepository.findByIdActive(id);
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
