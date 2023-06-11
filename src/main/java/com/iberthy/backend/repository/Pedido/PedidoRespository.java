package com.iberthy.backend.repository.Pedido;

import com.iberthy.backend.domain.entity.ClienteModel;
import com.iberthy.backend.domain.entity.pedido.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRespository extends JpaRepository<PedidoModel, Long> {

    @Query("select p from PedidoModel p left join fetch p.itens where p.id = :id ")
    PedidoModel findByIdFetchItens(@Param("id") Long id);

    List<PedidoModel> findByCliente(ClienteModel cliente);
}
