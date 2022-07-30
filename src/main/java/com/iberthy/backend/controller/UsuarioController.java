package com.iberthy.backend.controller;

import com.iberthy.backend.controller.dto.request.RequestUsuarioDTO;
import com.iberthy.backend.controller.dto.response.ResponseUsuarioDTO;
import com.iberthy.backend.domain.entity.Usuario;
import com.iberthy.backend.service.UsuarioService;
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
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public Page<ResponseUsuarioDTO> listarUsuarios(RequestUsuarioDTO filtro, Pageable pageable){
        var pageUsuarioDTO = usuarioService.findAll(filtro.transformIntoUsuario(filtro),pageable).map(u -> new ResponseUsuarioDTO(u));
        return pageUsuarioDTO;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseUsuarioDTO salvarUsuario(@Valid @RequestBody RequestUsuarioDTO usuarioDTO){

        var usuario = usuarioService.save(usuarioDTO.transformIntoUsuario(usuarioDTO));

        return new ResponseUsuarioDTO(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseUsuarioDTO> buscarUsuarioById(@PathVariable Long id){
        var usuarioDb = usuarioService.findById(id);

        if(usuarioDb == null){throw new ResponseStatusException(HttpStatus.NOT_FOUND, Message.usuarioNotFoud);}

        return ResponseEntity.ok(new ResponseUsuarioDTO(usuarioDb));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseUsuarioDTO> editarUsuarioById(@PathVariable Long id, @Valid @RequestBody RequestUsuarioDTO usuarioDTO){
        var usuario = usuarioService.edite(id, usuarioDTO.transformIntoUsuario(usuarioDTO));
        return ResponseEntity.ok(new ResponseUsuarioDTO(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuarioById(@PathVariable Long id){
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
