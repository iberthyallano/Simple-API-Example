package com.iberthy.backend.service;

import com.iberthy.backend.controller.dto.PedidoDTO;
import com.iberthy.backend.domain.entity.pedido.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PedidoService {

    Page<Pedido> findAll(PedidoDTO filtro, Pageable pageable);

    Pedido findById(Long id);

//    Ainda vendo com e quais os tipos de status que eu posso vir a por aqui!
//    Pedido findByStatus();

    Pedido save(PedidoDTO pedidoDTO);

    Pedido edite(Long id, PedidoDTO pedidoDTO);
}
