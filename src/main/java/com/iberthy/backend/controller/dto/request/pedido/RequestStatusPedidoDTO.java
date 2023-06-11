package com.iberthy.backend.controller.dto.request.pedido;

import com.iberthy.backend.domain.enums.StatusPedido;
import com.iberthy.backend.domain.validation.statusPedido.StatusPedidoValidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestStatusPedidoDTO {

    @StatusPedidoValidate
    private String status;

    public StatusPedido transformIntoStatusPedido(RequestStatusPedidoDTO statusPedidoDTO){
        return StatusPedido.valueOf(statusPedidoDTO.getStatus());
    }

}
