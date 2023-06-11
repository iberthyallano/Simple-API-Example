package com.iberthy.backend.controller.dto.request.pedido;

import com.iberthy.backend.domain.entity.ClienteModel;
import com.iberthy.backend.domain.entity.pedido.PedidoModel;
import com.iberthy.backend.domain.enums.StatusPedido;
import com.iberthy.backend.util.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
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

    public PedidoModel transformIntoPedido(ClienteModel cliente, StatusPedido status, LocalDateTime data, RequestPedidoDTO pedidoDTO){

        var pedido = new PedidoModel();

        pedido.setValorTotal(pedidoDTO.getValorTotal());
        pedido.setDataPedido(data);
        pedido.setCliente(cliente);
        pedido.setStatus(status);

        return pedido;
    }

}
