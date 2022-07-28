package com.iberthy.backend.service;

import com.iberthy.backend.domain.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

public interface ClienteService {

    Page<Cliente> findAll(Cliente filtro, Pageable pageable);

    Cliente findById(Long id);

    Cliente save(@Valid Cliente cliente);

    Cliente edite(Long id, Cliente cliente);

    Cliente delete(Long id);

}
