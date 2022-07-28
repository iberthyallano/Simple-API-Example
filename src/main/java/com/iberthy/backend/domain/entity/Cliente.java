package com.iberthy.backend.domain.entity;

import com.iberthy.backend.domain.abstracts.Pessoa;
import com.iberthy.backend.domain.entity.pedido.Pedido;
import com.iberthy.backend.util.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Cliente extends Pessoa {

    @NotNull(message = Message.saldoCarteiraNotNull)
    private Double saldoCarteira;

    @OneToMany( mappedBy = "cliente" , fetch = FetchType.LAZY )
    private Set<Pedido> pedidos;
}
