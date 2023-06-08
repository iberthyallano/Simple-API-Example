package com.iberthy.backend.service.implement;

import com.iberthy.backend.domain.entity.Cliente;
import com.iberthy.backend.exception.GenericException;
import com.iberthy.backend.repository.ClienteRepository;
import com.iberthy.backend.service.ClienteService;
import com.iberthy.backend.util.CommonMethods;
import com.iberthy.backend.util.Message;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class ClienteServiceImplement implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Page<Cliente> findAll(Cliente filtro, Pageable pageable){
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var matcher = ExampleMatcher.matching().withIgnoreCase()
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

            var cliente = clienteRepository.findAll(Example.of(filtro,matcher), pageable);

            log.warn("Executada com sucesso!");
            return cliente;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

    @Override
    public Cliente findById(Long id){
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var cliente = clienteRepository.findByIdActive(id);

            log.warn("Executada com sucesso!");
            return cliente;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente){
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var result = clienteRepository.save(cliente);

            log.warn("Executada com sucesso!");
            return result;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

    @Override
    @Transactional
    public Cliente edite(Long id, Cliente cliente){
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var clienteDb = clienteRepository.findByIdActive(id);

            if(clienteDb == null){throw new GenericException(Message.clienteInvalidId);}

            cliente.setId(clienteDb.getId());

            var result = clienteRepository.save(cliente);

            log.warn("Executada com sucesso!");
            return result;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

    @Override
    @Transactional
    public Cliente delete(Long id){
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var clienteDb = clienteRepository.findByIdActive(id);

            if(clienteDb == null){throw new GenericException(Message.clienteInvalidId);}

            clienteDb.setEnabled(false);

            var result = clienteRepository.save(clienteDb);

            log.warn("Executada com sucesso!");
            return result;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

}
