package com.iberthy.backend.controller;

import com.iberthy.backend.service.dto.request.pedido.RequestPedidoDTO;
import com.iberthy.backend.service.dto.request.pedido.RequestStatusPedidoDTO;
import com.iberthy.backend.domain.entity.pedido.Pedido;
import com.iberthy.backend.service.PedidoService;
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
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

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

    @PatchMapping("/{id}")
    public ResponseEntity<Pedido> editarUsuarioById(@PathVariable Long id, @Valid @RequestBody RequestStatusPedidoDTO status){
        pedidoService.alterarStatus(id, status);
        return ResponseEntity.noContent().build();
    }

}
