package com.iberthy.backend.controller;

import com.iberthy.backend.service.dto.request.RequestClienteDTO;
import com.iberthy.backend.domain.entity.Cliente;
import com.iberthy.backend.service.ClienteService;
import com.iberthy.backend.util.Message;
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
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public Page<Cliente> listarClientes(RequestClienteDTO filtro, Pageable pageable){
        return clienteService.findAll(filtro.transformIntoCliente(filtro),pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvarCliente(@Valid @RequestBody RequestClienteDTO cliente){
        return clienteService.save(cliente.transformIntoCliente(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClienteById(@PathVariable Long id){
        var clienteDb = clienteService.findById(id);

        if(clienteDb == null){throw new ResponseStatusException(HttpStatus.NOT_FOUND, Message.clienteNotFoud);}

        return ResponseEntity.ok(clienteDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editarClienteById(@PathVariable Long id, @Valid @RequestBody RequestClienteDTO cliente){
        return ResponseEntity.ok(clienteService.edite(id, cliente.transformIntoCliente(cliente)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable Long id){

        clienteService.delete(id);

        return ResponseEntity.noContent().build();
    }

}
