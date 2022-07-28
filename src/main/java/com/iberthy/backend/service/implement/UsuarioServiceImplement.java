package com.iberthy.backend.service.implement;

import com.iberthy.backend.domain.entity.Usuario;
import com.iberthy.backend.exception.GenericException;
import com.iberthy.backend.repository.UsuarioRepository;
import com.iberthy.backend.service.UsuarioService;
import com.iberthy.backend.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImplement implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Page<Usuario> findAll(Usuario filtro, Pageable pageable){

        var matcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(filtro,matcher);

        if(filtro.getAtivo() != false){
            filtro.setAtivo(true);
        }

        return usuarioRepository.findAll(example, pageable);
    }

    @Override
    public Usuario findById(Long id){
        return usuarioRepository.findByIdActive(id);
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario edite(Long id, Usuario usuario){
        var usuarioDb = usuarioRepository.findByIdActive(id);

        if(usuarioDb == null){throw new GenericException(Message.usuarioInvalidId);}

        usuario.setId(usuarioDb.getId());
        return this.save(usuario);
    }

    @Override
    @Transactional
    public Usuario delete(Long id){
        var usuarioDb = usuarioRepository.findByIdActive(id);

        if(usuarioDb == null){throw new GenericException(Message.usuarioInvalidId);}

        usuarioDb.setAtivo(false);
        return usuarioRepository.save(usuarioDb);
    }
}
