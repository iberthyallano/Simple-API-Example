package com.iberthy.backend.service.implement;

import com.iberthy.backend.domain.entity.UsuarioModel;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Log4j2
@Service
public class UsuarioServiceImplement implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UsuarioModel> findAll(UsuarioModel filtro){
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var matcher = ExampleMatcher.matching().withIgnoreCase()
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

            var page = usuarioRepository.findAll(Example.of(filtro,matcher));

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
    public UsuarioModel finByUsername(String nome) {
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
    public UsuarioModel save(UsuarioModel usuario){
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
    public Boolean updatePassword(Long id, String password) {
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var usuarioDb = usuarioRepository.findByIdActive(id);
            if(usuarioDb == null){throw new GenericException(Message.usuarioInvalidId);}

            usuarioDb.setPassword(passwordEncoder.encode(password));
            usuarioRepository.save(usuarioDb);

            log.warn("Executada com sucesso!");
            return true;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

    @Override
    @Transactional
    public UsuarioModel edite(Long id, UsuarioModel usuario){
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
    public Boolean delete(Long id){
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var usuarioDb = usuarioRepository.findByIdActive(id);
            if(usuarioDb == null){throw new GenericException(Message.usuarioInvalidId);}

            usuarioDb.setEnabled(false);
            usuarioRepository.save(usuarioDb);

            log.warn("Executada com sucesso!");
            return true;
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
