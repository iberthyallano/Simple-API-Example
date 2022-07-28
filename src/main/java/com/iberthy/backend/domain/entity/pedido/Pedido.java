package com.iberthy.backend.domain.entity.pedido;

import com.iberthy.backend.domain.entity.Cliente;
import com.iberthy.backend.domain.abstracts.AbstractEntity;
import com.iberthy.backend.utils.Message;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;


@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Pedido extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @NotNull(message = Message.valorTotalNotNull)
    @Min(value = 0, message = Message.valorTotalMin)
    @Column(name = "valor_total", precision = 20, scale = 2)
    private Double valorTotal;

    private LocalDateTime dataPedido;

    @OneToMany(mappedBy = "pedido")
    private List<ItemPedido> itens;

}