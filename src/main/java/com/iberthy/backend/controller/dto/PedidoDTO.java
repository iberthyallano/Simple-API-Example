package com.iberthy.backend.controller.dto;

import com.iberthy.backend.domain.entity.pedido.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDTO {
    private Long cliente;
    private Double total;
    private List<ItemPedidoDTO> items;
}
