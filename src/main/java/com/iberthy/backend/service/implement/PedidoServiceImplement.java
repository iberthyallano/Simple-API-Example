package com.iberthy.backend.service.implement;

import com.iberthy.backend.controller.dto.RequestItemPedidoDTO;
import com.iberthy.backend.controller.dto.RequestPedidoDTO;
import com.iberthy.backend.domain.entity.pedido.ItemPedido;
import com.iberthy.backend.domain.entity.pedido.Pedido;
import com.iberthy.backend.exception.GenericException;
import com.iberthy.backend.repository.Pedido.ItemPedidoRepository;
import com.iberthy.backend.repository.Pedido.PedidoRespository;
import com.iberthy.backend.service.ClienteService;
import com.iberthy.backend.service.PedidoService;
import com.iberthy.backend.service.ProdutoService;
import com.iberthy.backend.utils.Message;
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
    public Page<Pedido> findAll(RequestPedidoDTO filter, Pageable pageable) {
       return null;
    }

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
        var pedido = new Pedido();
        var cliente = clienteService.findById(requestPedidoDTO.getCliente());

        if(cliente == null){throw new GenericException(Message.clienteInvalidId);}

        pedido.setValorTotal(requestPedidoDTO.getValorTotal());
        pedido.setDataPedido(LocalDateTime.now());
        pedido.setCliente(cliente);

        var items = converterItems(pedido, requestPedidoDTO.getItems());
        pedidoRespository.save(pedido);
        itemPedidoRepository.saveAll(items);
        pedido.setItens(items);

        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido, List<RequestItemPedidoDTO> requestItemPedidoDTOList){

        if(requestItemPedidoDTOList.isEmpty()){throw new GenericException(Message.notSavePedidoAndItemsIsEmpty);}

        return requestItemPedidoDTOList.stream().map( dto -> {
            var produto = produtoService.findById(dto.getProduto());

            if(produto == null){throw new GenericException(Message.produtoInvalidId + "[id: "+dto.getProduto()+"]");}

            var item = new ItemPedido();
            item.setQuantidade(dto.getQuantidade());
            item.setPedido(pedido);
            item.setProduto(produto);

            return item;
        }).collect(Collectors.toList());

    }

    @Override
    public Pedido alterarStatus(Long id, RequestPedidoDTO requestPedidoDTO) {
        return null;
    }


}