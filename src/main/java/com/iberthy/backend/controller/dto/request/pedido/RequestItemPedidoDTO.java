package com.iberthy.backend.controller.dto.request.pedido;

import com.iberthy.backend.domain.entity.ProdutoModel;
import com.iberthy.backend.domain.entity.pedido.ItemPedidoModel;
import com.iberthy.backend.domain.entity.pedido.PedidoModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestItemPedidoDTO {

    private Long produto;
    private Integer quantidade;

    public ItemPedidoModel transformIntoItemPedido(PedidoModel pedido, ProdutoModel produto, RequestItemPedidoDTO itemPedidoDTO){

        var item = new ItemPedidoModel();
        item.setQuantidade(itemPedidoDTO.getQuantidade());
        item.setPedido(pedido);
        item.setProduto(produto);

        return item;
    }

}
