package com.iberthy.backend.controller;

import com.iberthy.backend.controller.dto.RequestUsuarioDTO;
import com.iberthy.backend.domain.entity.Usuario;
import com.iberthy.backend.service.UsuarioService;
import com.iberthy.backend.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Page<Usuario> listarUsuarios(RequestUsuarioDTO filtro, Pageable pageable){
        return usuarioService.findAll(filtro,pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario salvarUsuario(@Valid @RequestBody RequestUsuarioDTO usuario){
        return usuarioService.save(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioById(@PathVariable Long id){
        var usuarioDb = usuarioService.findById(id);

        if(usuarioDb == null){throw new ResponseStatusException(HttpStatus.NOT_FOUND, Message.usuarioNotFoud);}

        return ResponseEntity.ok(usuarioDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> editarUsuarioById(@PathVariable Long id, @Valid @RequestBody RequestUsuarioDTO usuario){
        return ResponseEntity.ok(usuarioService.edite(id, usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuarioById(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
