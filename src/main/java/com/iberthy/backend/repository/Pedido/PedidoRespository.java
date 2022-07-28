package com.iberthy.backend.repository.Pedido;

import com.iberthy.backend.domain.entity.Cliente;
import com.iberthy.backend.domain.entity.pedido.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PedidoRespository extends JpaRepository<Pedido, Long> {

    @Query("select p from Pedido p left join fetch p.itens where p.id = :id ")
    Pedido findByIdFetchItens(@Param("id") Long id);

    Page<Pedido> findByCliente(Cliente cliente, Pageable pageable);
}
