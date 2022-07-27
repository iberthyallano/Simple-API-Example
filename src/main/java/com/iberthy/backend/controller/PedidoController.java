package com.iberthy.backend.controller;

import com.iberthy.backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

//    @GetMapping
//    public Page<Usuario> listarUsuarios(Usuario filtro, Pageable pageable){
//        return usuarioService.findAll(filtro,pageable);
//    }
//
//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public Usuario salvarUsuario(@Valid @RequestBody Usuario usuario){
//        return usuarioService.save(usuario);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Usuario> buscarUsuarioById(@PathVariable Long id){
//        var usuarioDb = usuarioService.findById(id);
//        return usuarioDb != null ? ResponseEntity.ok(usuarioDb) : ResponseEntity.notFound().build();
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Usuario> editarUsuarioById(@PathVariable Long id, @Valid @RequestBody Usuario usuario){
//        var usuarioDb= usuarioService.edite(id, usuario);
//        return usuarioDb != null ? ResponseEntity.ok(usuarioDb) : ResponseEntity.notFound().build();
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUsuarioById(@PathVariable Long id){
//        var usuarioDb= usuarioService.delete(id);
//        return usuarioDb != null ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
//    }

}
