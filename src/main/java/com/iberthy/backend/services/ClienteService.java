package com.iberthy.backend.services;

import com.iberthy.backend.models.Cliente;
import com.iberthy.backend.repositorys.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll(){
        return clienteRepository.findAllActive();
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
