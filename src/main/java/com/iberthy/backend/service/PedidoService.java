package com.iberthy.backend.service;

import com.iberthy.backend.controller.dto.RequestPedidoDTO;
import com.iberthy.backend.domain.entity.pedido.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoService {

    Page<Pedido> findAll(RequestPedidoDTO filtro, Pageable pageable);

    Page<Pedido> findAllByCliente(Long clienteId, Pageable pageable);

    Pedido findById(Long id);

    Pedido save(RequestPedidoDTO requestPedidoDTO);

    Pedido alterarStatus(Long id, RequestPedidoDTO requestPedidoDTO);
}
