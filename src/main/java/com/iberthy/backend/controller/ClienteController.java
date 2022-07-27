package com.iberthy.backend.controller;

import com.iberthy.backend.model.Cliente;
import com.iberthy.backend.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public Page<Cliente> listarClientes(Pageable pageable){
        return clienteService.findAll(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvarCliente(@Valid @RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClienteById(@PathVariable Long id){
        var clienteDb = clienteService.findById(id);
        return clienteDb != null ? ResponseEntity.ok(clienteDb) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editarClienteById(@PathVariable Long id, @Valid @RequestBody Cliente cliente){
        var clienteDb= clienteService.edite(id, cliente);
        return clienteDb != null ? ResponseEntity.ok(clienteDb) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable Long id){
        var clienteDb= clienteService.delete(id);
        return clienteDb != null ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
