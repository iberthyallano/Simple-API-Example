package com.iberthy.backend.controller;

import com.iberthy.backend.controller.Mapper.UsuarioMapper;
import com.iberthy.backend.controller.dto.request.usuario.UsuarioFilterDTO;
import com.iberthy.backend.controller.dto.request.usuario.UsuarioSaveDTO;
import com.iberthy.backend.controller.dto.request.usuario.UsuarioUpdSenhaDTO;
import com.iberthy.backend.controller.dto.response.usuario.UsuarioDTO;
import com.iberthy.backend.service.UsuarioService;
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
@RequestMapping("/usuarios")
@Api(tags = "Usuário", description = " ")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    @ApiOperation("Criação de um usuário")
    public ResponseEntity<UsuarioDTO> salvarUsuario(@Valid @RequestBody UsuarioSaveDTO usuario){
        var model = usuarioMapper.mapUsuarioSaveDTOToModel(usuario);
        var response = usuarioMapper.mapUsuarioModelToUsuarioDTO(usuarioService.save(model));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualização de dados de um usuário específico por ID")
    public ResponseEntity<UsuarioDTO> editarUsuarioById(@PathVariable Long id, @Valid @RequestBody UsuarioSaveDTO usuario){
        var model = usuarioMapper.mapUsuarioSaveDTOToModel(usuario);
        var response = usuarioMapper.mapUsuarioModelToUsuarioDTO(usuarioService.edite(id, model));
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    @ApiOperation("Atualização de senha do usuário específico por ID")
    public ResponseEntity<Boolean> editarUsuarioById(@PathVariable Long id, @Valid @RequestBody UsuarioUpdSenhaDTO upd){
        var response = usuarioService.updatePassword(id, upd.getPassword());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    @ApiOperation("Listagem de usuários")
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios(UsuarioFilterDTO filtro){
        var model = usuarioMapper.mapUsuarioFilterDTOToModel(filtro);
        var response = usuarioMapper.mapUsuarioModelToUsuarioDTO(usuarioService.findAll(model));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMINISTRADOR')")
    @ApiOperation("Exclusão de dados de um usuário específico por ID")
    public ResponseEntity<Boolean> deleteUsuarioById(@PathVariable Long id){
        var response = usuarioService.delete(id);
        return ResponseEntity.ok(response);
    }

}
