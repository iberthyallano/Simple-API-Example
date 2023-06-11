package com.iberthy.backend.domain.entity;

import com.iberthy.backend.domain.abstracts.PessoaModel;
import com.iberthy.backend.domain.entity.pedido.PedidoModel;
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
public class ClienteModel extends PessoaModel {

    @NotNull(message = Message.saldoCarteiraNotNull)
    private Double saldoCarteira;

    @OneToMany( mappedBy = "cliente" , fetch = FetchType.LAZY )
    private Set<PedidoModel> pedidos;

}
