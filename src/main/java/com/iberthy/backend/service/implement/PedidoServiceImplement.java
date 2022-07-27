package com.iberthy.backend.service.implement;

import com.iberthy.backend.repository.Pedido.ItemPedidoRepository;
import com.iberthy.backend.repository.Pedido.PedidoRespository;
import com.iberthy.backend.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImplement implements PedidoService {

    @Autowired
    private PedidoRespository pedidoRespository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;


}
