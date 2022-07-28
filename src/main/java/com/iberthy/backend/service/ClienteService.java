package com.iberthy.backend.service;

import com.iberthy.backend.controller.dto.RequestClienteDTO;
import com.iberthy.backend.domain.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.validation.Valid;

public interface ClienteService {

    Page<Cliente> findAll(RequestClienteDTO filtro, Pageable pageable);

    Cliente findById(Long id);

    Cliente save(@Valid RequestClienteDTO requestClienteDTO);

    Cliente edite(Long id, RequestClienteDTO requestClienteDTO);

    Cliente delete(Long id);

}
