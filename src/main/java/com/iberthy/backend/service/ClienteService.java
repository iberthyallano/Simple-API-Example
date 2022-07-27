package com.iberthy.backend.service;

import com.iberthy.backend.model.Cliente;
import com.iberthy.backend.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Page<Cliente> findAll( Pageable pageable){
        return clienteRepository.findAllActive(pageable);
    }

    public Cliente findById(Long id){
        return clienteRepository.findByIdActive(id);
    }

    public Cliente findByNome(String nome){
        return clienteRepository.findByNomeContaining(nome);
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
