package com.iberthy.backend.service;

import com.iberthy.backend.model.Cliente;
import com.iberthy.backend.model.Usuario;
import com.iberthy.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Page<Cliente> findAll(Cliente filtro, Pageable pageable){

        var matcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(filtro,matcher);

        if(filtro.getAtivo() != false){
            filtro.setAtivo(true);
        }

        return clienteRepository.findAll(example, pageable);
    }

    public Cliente findById(Long id){
        return clienteRepository.findByIdActive(id);
    }

    @Transactional
    public Cliente save(Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @Transactional
    public Cliente edite(Long id, Cliente cliente){
        var clienteDb = clienteRepository.findByIdActive(id);

        if(clienteDb != null){
            cliente.setId(clienteDb.getId());
            return this.save(cliente);
        }

        return null;
    }

    @Transactional
    public Cliente delete(Long id){
        var clienteDb = clienteRepository.findByIdActive(id);

        if(clienteDb != null){
            clienteDb.setAtivo(false);
            return clienteRepository.save(clienteDb);
        }

        return null;
    }

}
