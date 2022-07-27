package com.iberthy.backend.repository.Pedido;

import com.iberthy.backend.domain.entity.pedido.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRespository extends JpaRepository<Pedido, Long> {
}
