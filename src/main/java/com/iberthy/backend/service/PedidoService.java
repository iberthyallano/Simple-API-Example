package com.iberthy.backend.service;

import com.iberthy.backend.controller.dto.request.pedido.RequestPedidoDTO;
import com.iberthy.backend.controller.dto.request.pedido.RequestStatusPedidoDTO;
import com.iberthy.backend.domain.entity.pedido.PedidoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PedidoService {

    List<PedidoModel> findAllByCliente(Long clienteId);

    PedidoModel findById(Long id);

    PedidoModel save(RequestPedidoDTO requestPedidoDTO);

    Boolean alterarStatus(Long id, RequestStatusPedidoDTO requestStatusPedidoDTO);
}
