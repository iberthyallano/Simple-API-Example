package com.iberthy.backend.service;

import com.iberthy.backend.domain.entity.ClienteModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClienteService {

    List<ClienteModel> findAll(ClienteModel filtro);

    ClienteModel findById(Long id);

    ClienteModel save(ClienteModel cliente);

    ClienteModel edite(Long id, ClienteModel cliente);

    Boolean delete(Long id);

}
