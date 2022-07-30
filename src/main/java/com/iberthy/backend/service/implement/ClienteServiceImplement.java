package com.iberthy.backend.service.implement;

import com.iberthy.backend.domain.entity.Cliente;
import com.iberthy.backend.exception.GenericException;
import com.iberthy.backend.repository.ClienteRepository;
import com.iberthy.backend.service.ClienteService;
import com.iberthy.backend.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteServiceImplement implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public Page<Cliente> findAll(Cliente filtro, Pageable pageable){

        var matcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        return clienteRepository.findAll(Example.of(filtro,matcher), pageable);
    }

    @Override
    public Cliente findById(Long id){
        return clienteRepository.findByIdActive(id);
    }

    @Override
    @Transactional
    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public Cliente edite(Long id, Cliente cliente){
        var clienteDb = clienteRepository.findByIdActive(id);

        if(clienteDb == null){throw new GenericException(Message.clienteInvalidId);}

        cliente.setId(clienteDb.getId());

        return clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public Cliente delete(Long id){
        var clienteDb = clienteRepository.findByIdActive(id);

        if(clienteDb == null){throw new GenericException(Message.clienteInvalidId);}

        clienteDb.setEnabled(false);

        return clienteRepository.save(clienteDb);
    }

}
