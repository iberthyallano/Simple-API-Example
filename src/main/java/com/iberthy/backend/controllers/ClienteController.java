package com.iberthy.backend.controllers;

import com.iberthy.backend.models.Cliente;
import com.iberthy.backend.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarClientes(){
        return clienteService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvarCliente(@Valid @RequestBody Cliente cliente){
        return clienteService.save(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarClienteById(@PathVariable Long id){

        var cliente = clienteService.findById(id);

        if(cliente != null){return ResponseEntity.ok(cliente);}

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editarClienteById(@PathVariable Long id, @Valid @RequestBody Cliente cliente){
        var clienteDb= clienteService.edite(id, cliente);

        if(clienteDb != null){return ResponseEntity.ok(clienteDb);}

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClienteById(@PathVariable Long id){
        var clienteDb= clienteService.delete(id);

        if(clienteDb != null){return ResponseEntity.noContent().build();}

        return ResponseEntity.notFound().build();
    }
}
