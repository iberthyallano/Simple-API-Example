package com.iberthy.backend.domain.entity.pedido;

import com.iberthy.backend.domain.entity.Produto;
import com.iberthy.backend.domain.abstracts.AbstractEntity;
import com.iberthy.backend.utils.Message;
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
public class ItemPedido extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @NotNull(message = Message.quantidadeNotNull)
    @Min(value = 1, message = Message.quantidadeMin)
    private Integer quantidade;

}
