package com.iberthy.backend.service.implement;

import com.iberthy.backend.domain.entity.Produto;
import com.iberthy.backend.exception.GenericException;
import com.iberthy.backend.repository.ProdutoRepository;
import com.iberthy.backend.service.ProdutoService;
import com.iberthy.backend.util.CommonMethods;
import com.iberthy.backend.util.Message;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class ProdutoServiceImplement implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Page<Produto> findAll(Produto filtro, Pageable pageable){
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var matcher = ExampleMatcher.matching().withIgnoreCase()
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

            var example = Example.of(filtro,matcher);

            if(filtro.isEnabled() != false){
                filtro.setEnabled(true);
            }

            var page = produtoRepository.findAll(example, pageable);

            log.warn("Executada com sucesso!");
            return page;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

    public Produto findById(Long id){
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var prod = produtoRepository.findByIdActive(id);

            log.warn("Executada com sucesso!");
            return prod;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

    @Transactional
    public Produto save(Produto produto){
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var prod = produtoRepository.save(produto);

            log.warn("Executada com sucesso!");
            return prod;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

    @Transactional
    public Produto edite(Long id, Produto produto){
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var produtoDb = produtoRepository.findByIdActive(id);

            if(produtoDb == null){throw new GenericException(Message.produtoInvalidId);}

            produto.setId(produtoDb.getId());

            var prod = produtoRepository.save(produto);

            log.warn("Executada com sucesso!");
            return prod;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

    @Transactional
    public Produto delete(Long id){
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var produtoDb = produtoRepository.findByIdActive(id);

            if(produtoDb == null){throw new GenericException(Message.produtoInvalidId);}

            produtoDb.setEnabled(false);

            var prod = produtoRepository.save(produtoDb);

            log.warn("Executada com sucesso!");
            return prod;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

}
