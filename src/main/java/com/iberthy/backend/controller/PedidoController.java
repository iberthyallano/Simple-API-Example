package com.iberthy.backend.controller;

import com.iberthy.backend.controller.dto.PedidoDTO;
import com.iberthy.backend.domain.entity.pedido.Pedido;
import com.iberthy.backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido salvarUsuario(@RequestBody PedidoDTO pedidoDTO){
        return pedidoService.save(pedidoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarUsuarioById(@PathVariable Long id){
        var pedidoDB = pedidoService.findById(id);
        return pedidoDB != null ? ResponseEntity.ok(pedidoDB) : ResponseEntity.notFound().build();
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Usuario> editarUsuarioById(@PathVariable Long id, @Valid @RequestBody Usuario usuario){
//        var usuarioDb= usuarioService.edite(id, usuario);
//        return usuarioDb != null ? ResponseEntity.ok(usuarioDb) : ResponseEntity.notFound().build();
//    }

}
