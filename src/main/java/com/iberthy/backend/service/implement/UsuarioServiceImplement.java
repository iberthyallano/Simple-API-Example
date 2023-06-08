package com.iberthy.backend.service.implement;

import com.iberthy.backend.domain.entity.Usuario;
import com.iberthy.backend.exception.GenericException;
import com.iberthy.backend.repository.UsuarioRepository;
import com.iberthy.backend.service.UsuarioService;
import com.iberthy.backend.util.CommonMethods;
import com.iberthy.backend.util.Message;
import lombok.extern.log4j.Log4j2;
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

@Log4j2
@Service
public class UsuarioServiceImplement implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<Usuario> findAll(Usuario filtro, Pageable pageable){
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var matcher = ExampleMatcher.matching().withIgnoreCase()
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

            var page = usuarioRepository.findAll(Example.of(filtro,matcher), pageable);

            log.warn("Executada com sucesso!");
            return page;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

    @Override
    public Usuario findById(Long id){
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var user =  usuarioRepository.findByIdActive(id);

            log.warn("Executada com sucesso!");
            return user;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

    @Override
    public Usuario finByUsername(String nome) {
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var user =  usuarioRepository.findByUsernameActive(nome);

            log.warn("Executada com sucesso!");
            return user;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

    @Override
    @Transactional
    public Usuario save(Usuario usuario){
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            var user = usuarioRepository.save(usuario);

            log.warn("Executada com sucesso!");
            return user;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

    @Override
    @Transactional
    public Usuario edite(Long id, Usuario usuario){
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var usuarioDb = usuarioRepository.findByIdActive(id);
            if(usuarioDb == null){throw new GenericException(Message.usuarioInvalidId);}

            usuario.setId(usuarioDb.getId());
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
            var user = usuarioRepository.save(usuario);

            log.warn("Executada com sucesso!");
            return user;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

    @Override
    @Transactional
    public Usuario delete(Long id){
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var usuarioDb = usuarioRepository.findByIdActive(id);
            if(usuarioDb == null){throw new GenericException(Message.usuarioInvalidId);}

            usuarioDb.setEnabled(false);
            var user = usuarioRepository.save(usuarioDb);

            log.warn("Executada com sucesso!");
            return user;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var usuarioDb = usuarioRepository.findByUsernameActive(username);
            if(usuarioDb == null){throw new UsernameNotFoundException(Message.usuarioNotFoud);}

            log.warn("Executada com sucesso!");
            return usuarioDb;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }
}
