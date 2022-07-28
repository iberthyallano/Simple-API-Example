package com.iberthy.backend.controller;

import com.iberthy.backend.controller.dto.RequestPedidoDTO;
import com.iberthy.backend.domain.entity.pedido.Pedido;
import com.iberthy.backend.service.PedidoService;
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
    public Pedido salvarPedido(@Valid @RequestBody RequestPedidoDTO pedidoDTO){
        return pedidoService.save(pedidoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoById(@PathVariable Long id){
        var pedidoDB = pedidoService.findById(id);

        if(pedidoDB == null){throw new ResponseStatusException(HttpStatus.NOT_FOUND, Message.pedidoNotFoud);}

        return ResponseEntity.ok(pedidoDB);
    }

    @GetMapping("/cliente/{clienteId}")
    public Page<Pedido> listarPedidosByCliente(@PathVariable Long clienteId, Pageable pageable){
        return pedidoService.findAllByCliente(clienteId, pageable);
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<Usuario> editarUsuarioById(@PathVariable Long id, @Valid @RequestBody Usuario usuario){
//        var usuarioDb= usuarioService.edite(id, usuario);
//        return usuarioDb != null ? ResponseEntity.ok(usuarioDb) : ResponseEntity.notFound().build();
//    }

}
