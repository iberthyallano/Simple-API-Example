package com.iberthy.backend.controller.dto.request.pedido;

import com.iberthy.backend.domain.entity.Produto;
import com.iberthy.backend.domain.entity.pedido.ItemPedido;
import com.iberthy.backend.domain.entity.pedido.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestItemPedidoDTO {

    private Long produto;
    private Integer quantidade;

    public ItemPedido transformIntoItemPedido(Pedido pedido, Produto produto, RequestItemPedidoDTO itemPedidoDTO){

        var item = new ItemPedido();
        item.setQuantidade(itemPedidoDTO.getQuantidade());
        item.setPedido(pedido);
        item.setProduto(produto);

        return item;
    }

}
