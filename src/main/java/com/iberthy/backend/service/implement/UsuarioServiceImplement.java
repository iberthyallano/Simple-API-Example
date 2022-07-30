package com.iberthy.backend.service.implement;

import com.iberthy.backend.controller.dto.RequestUsuarioDTO;
import com.iberthy.backend.domain.entity.Usuario;
import com.iberthy.backend.exception.GenericException;
import com.iberthy.backend.repository.UsuarioRepository;
import com.iberthy.backend.service.UsuarioService;
import com.iberthy.backend.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioServiceImplement implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<Usuario> findAll(RequestUsuarioDTO filtro, Pageable pageable){

        var matcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var usuario = filtro.transformIntoUsuario(filtro);

        return usuarioRepository.findAll(Example.of(usuario,matcher), pageable);
    }

    @Override
    public Usuario findById(Long id){
        return usuarioRepository.findByIdActive(id);
    }

    @Override
    public Usuario finByUsername(String nome) {
        return usuarioRepository.findByUsernameActive(nome);
    }

    @Override
    @Transactional
    public Usuario save(RequestUsuarioDTO requestUsuarioDTO){

        var usuario = requestUsuarioDTO.transformIntoUsuario(requestUsuarioDTO);

        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario edite(Long id, RequestUsuarioDTO requestUsuarioDTO){
        var usuarioDb = usuarioRepository.findByIdActive(id);

        if(usuarioDb == null){throw new GenericException(Message.usuarioInvalidId);}

        var usuario = requestUsuarioDTO.transformIntoUsuario(requestUsuarioDTO);

        usuario.setId(usuarioDb.getId());
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario delete(Long id){
        var usuarioDb = usuarioRepository.findByIdActive(id);

        if(usuarioDb == null){throw new GenericException(Message.usuarioInvalidId);}

        usuarioDb.setEnabled(false);

        return usuarioRepository.save(usuarioDb);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var usuarioDb = usuarioRepository.findByUsernameActive(username);

        if(usuarioDb == null){throw new UsernameNotFoundException(Message.usuarioNotFoud);}

        return usuarioDb;
    }
}
