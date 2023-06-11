package com.iberthy.backend.domain.entity.pedido;

import com.iberthy.backend.domain.entity.ProdutoModel;
import com.iberthy.backend.domain.abstracts.AbstractEntity;
import com.iberthy.backend.util.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "item_pedido")
public class ItemPedidoModel extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoModel pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private ProdutoModel produto;

    @NotNull(message = Message.quantidadeNotNull)
    @Min(value = 1, message = Message.quantidadeMin)
    private Integer quantidade;

}
