package com.iberthy.backend.controller.dto;

import com.iberthy.backend.utils.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestPedidoDTO {

    @NotNull(message = Message.clienteInvalidId)
    private Long cliente;

    @NotNull(message = Message.valorTotalNotNull)
    @Min(value = 0, message = Message.valorTotalMin)
    private Double valorTotal;

    private List<RequestItemPedidoDTO> items;
}
