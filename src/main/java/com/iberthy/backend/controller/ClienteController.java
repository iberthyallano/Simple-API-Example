package com.iberthy.backend.controller;

import com.iberthy.backend.controller.Mapper.ClienteMapper;
import com.iberthy.backend.controller.dto.request.cliente.ClienteFilterDTO;
import com.iberthy.backend.controller.dto.request.cliente.ClienteSaveDTO;
import com.iberthy.backend.controller.dto.response.cliente.ClienteDTO;
import com.iberthy.backend.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'FUNCIONARIO')")
@RequestMapping("/clientes")
@Api(tags = "Cliente", description = " ")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ClienteMapper clienteMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Criação de um cliente")
    public ResponseEntity<ClienteDTO> salvarCliente(@Valid @RequestBody ClienteSaveDTO cliente){
        var model = clienteMapper.mapClienteSaveDTOToModel(cliente);
        var response = clienteMapper.mapClienteModelToClienteDTO(clienteService.save(model));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualização de dados de um cliente específico por ID")
    public ResponseEntity<ClienteDTO> editarClienteById(@PathVariable Long id, @Valid @RequestBody ClienteSaveDTO cliente){
        var model = clienteMapper.mapClienteSaveDTOToModel(cliente);
        var response = clienteMapper.mapClienteModelToClienteDTO(clienteService.edite(id, model));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @ApiOperation("Listagem de clientes")
    public ResponseEntity<List<ClienteDTO>> listarClientes(ClienteFilterDTO filtro){
        var model = clienteMapper.mapClienteFilterDTOToModel(filtro);
        var response = clienteMapper.mapClienteModelToClienteDTO(clienteService.findAll(model));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Exclusão de dados de um cliente específico por ID")
    public ResponseEntity<Boolean> deleteClienteById(@PathVariable Long id){
        var response = clienteService.delete(id);
        return ResponseEntity.ok(response);
    }

}
