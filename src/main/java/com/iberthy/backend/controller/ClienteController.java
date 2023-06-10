package com.iberthy.backend.controller;

import com.iberthy.backend.service.dto.request.RequestClienteDTO;
import com.iberthy.backend.domain.entity.Cliente;
import com.iberthy.backend.service.ClienteService;
import com.iberthy.backend.util.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'FUNCIONARIO')")
@RequestMapping("/clientes")
@Api(tags = "Cliente", description = " ")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    @ApiOperation("Listagem de clientes")
    public Page<Cliente> listarClientes(RequestClienteDTO filtro, Pageable pageable){
        return clienteService.findAll(filtro.transformIntoCliente(filtro),pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Criação de um cliente")
    public Cliente salvarCliente(@Valid @RequestBody RequestClienteDTO cliente){
        return clienteService.save(cliente.transformIntoCliente(cliente));
    }

    @GetMapping("/{id}")
    @ApiOperation("Busca de um cliente específico por ID")
    public ResponseEntity<Cliente> buscarClienteById(@PathVariable Long id){
        var clienteDb = clienteService.findById(id);

        if(clienteDb == null){throw new ResponseStatusException(HttpStatus.NOT_FOUND, Message.clienteNotFoud);}

        return ResponseEntity.ok(clienteDb);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualização de dados de um cliente específico por ID")
    public ResponseEntity<Cliente> editarClienteById(@PathVariable Long id, @Valid @RequestBody RequestClienteDTO cliente){
        return ResponseEntity.ok(clienteService.edite(id, cliente.transformIntoCliente(cliente)));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Exclusão de dados de um cliente específico por ID")
    public ResponseEntity<Void> deleteClienteById(@PathVariable Long id){

        clienteService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
