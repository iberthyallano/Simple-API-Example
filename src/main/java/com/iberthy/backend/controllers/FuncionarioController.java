package com.iberthy.backend.controllers;

import com.iberthy.backend.models.Funcionario;
import com.iberthy.backend.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<Funcionario> listarFuncionarios(){
        return funcionarioService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario salvarFuncionario(@Valid @RequestBody Funcionario cliente){
        return funcionarioService.save(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarFuncionarioById(@PathVariable Long id){

        var funcionario = funcionarioService.findById(id);

        if(funcionario != null){return ResponseEntity.ok(funcionario);}

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Funcionario> editarFuncionarioById(@PathVariable Long id, @Valid @RequestBody Funcionario cliente){
        var funcionarioDb= funcionarioService.edite(id, cliente);

        if(funcionarioDb != null){return ResponseEntity.ok(funcionarioDb);}

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFuncionarioById(@PathVariable Long id){
        var funcionarioDb= funcionarioService.delete(id);

        if(funcionarioDb != null){return ResponseEntity.noContent().build();}

        return ResponseEntity.notFound().build();
    }
}
