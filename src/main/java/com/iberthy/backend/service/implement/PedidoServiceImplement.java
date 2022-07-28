package com.iberthy.backend.service.implement;

import com.iberthy.backend.controller.dto.pedido.RequestItemPedidoDTO;
import com.iberthy.backend.controller.dto.pedido.RequestPedidoDTO;
import com.iberthy.backend.controller.dto.pedido.RequestStatusPedidoDTO;
import com.iberthy.backend.domain.entity.pedido.ItemPedido;
import com.iberthy.backend.domain.entity.pedido.Pedido;
import com.iberthy.backend.domain.enums.StatusPedido;
import com.iberthy.backend.exception.GenericException;
import com.iberthy.backend.repository.Pedido.ItemPedidoRepository;
import com.iberthy.backend.repository.Pedido.PedidoRespository;
import com.iberthy.backend.service.ClienteService;
import com.iberthy.backend.service.PedidoService;
import com.iberthy.backend.service.ProdutoService;
import com.iberthy.backend.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImplement implements PedidoService {

    @Autowired
    private PedidoRespository pedidoRespository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @Override
    public Page<Pedido> findAllByCliente(Long clienteId, Pageable pageable) {
        var cliente = clienteService.findById(clienteId);

        if(cliente == null){throw new GenericException(Message.clienteInvalidId);}

        return pedidoRespository.findByCliente(cliente,pageable);
    }

    @Override
    public Pedido findById(Long id) {
        return pedidoRespository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public Pedido save(RequestPedidoDTO requestPedidoDTO) {
        var cliente = clienteService.findById(requestPedidoDTO.getCliente());

        if(cliente == null){throw new GenericException(Message.clienteInvalidId);}

        var pedido = requestPedidoDTO.transformIntoPedido(cliente, StatusPedido.REALIZADO, LocalDateTime.now(), requestPedidoDTO);

        pedidoRespository.save(pedido);

        var items = converterItems(pedido, requestPedidoDTO.getItems());
        itemPedidoRepository.saveAll(items);
        pedido.setItens(items);

        return pedido;
    }

    @Override
    @Transactional
    public Pedido alterarStatus(Long id, RequestStatusPedidoDTO requestStatusPedidoDTO) {
        var pedido = pedidoRespository.findByIdFetchItens(id);

        if(pedido == null){throw new GenericException(Message.pedidoInvalidId);}

        var status = requestStatusPedidoDTO.transformIntoStatusPedido(requestStatusPedidoDTO);

        pedido.setStatus(status);

        return  pedidoRespository.save(pedido);
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<RequestItemPedidoDTO> requestItemPedidoDTOList){

        if(requestItemPedidoDTOList.isEmpty()){throw new GenericException(Message.notSavePedidoAndItemsIsEmpty);}

        return requestItemPedidoDTOList.stream().map( dto -> {
            var produto = produtoService.findById(dto.getProduto());

            if(produto == null){throw new GenericException(Message.produtoInvalidId + "[id: "+dto.getProduto()+"]");}

            return dto.transformIntoItemPedido(pedido, produto, dto);
        }).collect(Collectors.toList());

    }



}
