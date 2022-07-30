package com.iberthy.backend.controller.dto.request.pedido;

import com.iberthy.backend.domain.enums.StatusPedido;
import com.iberthy.backend.validation.sexo.SexoValidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestStatusPedidoDTO {

    @SexoValidate
    private String status;

    public StatusPedido transformIntoStatusPedido(RequestStatusPedidoDTO statusPedidoDTO){
        return StatusPedido.valueOf(statusPedidoDTO.getStatus());
    }

}
