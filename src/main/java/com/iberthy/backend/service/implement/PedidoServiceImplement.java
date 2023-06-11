package com.iberthy.backend.service.implement;

import com.iberthy.backend.controller.dto.request.pedido.RequestItemPedidoDTO;
import com.iberthy.backend.controller.dto.request.pedido.RequestPedidoDTO;
import com.iberthy.backend.controller.dto.request.pedido.RequestStatusPedidoDTO;
import com.iberthy.backend.domain.entity.pedido.ItemPedidoModel;
import com.iberthy.backend.domain.entity.pedido.PedidoModel;
import com.iberthy.backend.domain.enums.StatusPedido;
import com.iberthy.backend.exception.GenericException;
import com.iberthy.backend.repository.Pedido.ItemPedidoRepository;
import com.iberthy.backend.repository.Pedido.PedidoRespository;
import com.iberthy.backend.service.ClienteService;
import com.iberthy.backend.service.PedidoService;
import com.iberthy.backend.service.ProdutoService;
import com.iberthy.backend.util.CommonMethods;
import com.iberthy.backend.util.Message;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Log4j2
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
    public List<PedidoModel> findAllByCliente(Long clienteId) {
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var cliente = clienteService.findById(clienteId);

            if(cliente == null){throw new GenericException(Message.clienteInvalidId);}

            var page = pedidoRespository.findByCliente(cliente);

            log.warn("Executada com sucesso!");
            return page;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

    @Override
    public PedidoModel findById(Long id) {
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var page = pedidoRespository.findByIdFetchItens(id);

            log.warn("Executada com sucesso!");
            return page;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

    @Override
    @Transactional
    public PedidoModel save(RequestPedidoDTO requestPedidoDTO) {
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var cliente = clienteService.findById(requestPedidoDTO.getCliente());

            if(cliente == null){throw new GenericException(Message.clienteInvalidId);}

            var pedido = requestPedidoDTO.transformIntoPedido(cliente, StatusPedido.REALIZADO, LocalDateTime.now(), requestPedidoDTO);

            pedidoRespository.save(pedido);

            var items = converterItems(pedido, requestPedidoDTO.getItems());
            itemPedidoRepository.saveAll(items);
            pedido.setItens(items);

            log.warn("Executada com sucesso!");
            return pedido;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

    @Override
    @Transactional
    public Boolean alterarStatus(Long id, RequestStatusPedidoDTO requestStatusPedidoDTO) {
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var pedido = pedidoRespository.findByIdFetchItens(id);

            if(pedido == null){throw new GenericException(Message.pedidoInvalidId);}

            var status = requestStatusPedidoDTO.transformIntoStatusPedido(requestStatusPedidoDTO);

            pedido.setStatus(status);
            pedidoRespository.save(pedido);

            log.warn("Executada com sucesso!");
            return true;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

    private List<ItemPedidoModel> converterItems(PedidoModel pedido, List<RequestItemPedidoDTO> requestItemPedidoDTOList){
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            if(requestItemPedidoDTOList.isEmpty()){throw new GenericException(Message.notSavePedidoAndItemsIsEmpty);}

            var pedidos = requestItemPedidoDTOList.stream().map( dto -> {
                var produto = produtoService.findById(dto.getProduto());

                if(produto == null){throw new GenericException(Message.produtoInvalidId + "[id: "+dto.getProduto()+"]");}

                return dto.transformIntoItemPedido(pedido, produto, dto);
            }).collect(Collectors.toList());

            log.warn("Executada com sucesso!");
            return pedidos;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

}
