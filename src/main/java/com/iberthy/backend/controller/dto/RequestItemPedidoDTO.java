package com.iberthy.backend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestItemPedidoDTO {
    private Long Produto;
    private Integer quantidade;
}
