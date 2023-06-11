package com.iberthy.backend.repository.Pedido;

import com.iberthy.backend.domain.entity.pedido.ItemPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedidoModel, Long> {
}
