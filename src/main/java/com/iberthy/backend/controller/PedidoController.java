package com.iberthy.backend.controller;

import com.iberthy.backend.service.dto.request.pedido.RequestPedidoDTO;
import com.iberthy.backend.service.dto.request.pedido.RequestStatusPedidoDTO;
import com.iberthy.backend.domain.entity.pedido.Pedido;
import com.iberthy.backend.service.PedidoService;
import com.iberthy.backend.util.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "Pedido", description = " ")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Criação de um pedido")
    public Pedido salvarPedido(@Valid @RequestBody RequestPedidoDTO pedidoDTO){
        return pedidoService.save(pedidoDTO);
    }

    @GetMapping("/{id}")
    @ApiOperation("Busca de um pedido específico por ID")
    public ResponseEntity<Pedido> buscarPedidoById(@PathVariable Long id){
        var pedidoDB = pedidoService.findById(id);

        if(pedidoDB == null){throw new ResponseStatusException(HttpStatus.NOT_FOUND, Message.pedidoNotFoud);}

        return ResponseEntity.ok(pedidoDB);
    }

    @GetMapping("/cliente/{clienteId}")
    @ApiOperation("Busca dos pedidos de um cliente por ID do cliente")
    public Page<Pedido> listarPedidosByCliente(@PathVariable Long clienteId, Pageable pageable){
        return pedidoService.findAllByCliente(clienteId, pageable);
    }

    @PatchMapping("/{id}")
    @ApiOperation("Atualização de dados de um pedido específico por ID")
    public ResponseEntity<Pedido> editarPedidoById(@PathVariable Long id, @Valid @RequestBody RequestStatusPedidoDTO status){
        pedidoService.alterarStatus(id, status);
        return ResponseEntity.noContent().build();
    }

}
