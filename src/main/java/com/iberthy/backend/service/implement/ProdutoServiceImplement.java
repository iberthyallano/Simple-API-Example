package com.iberthy.backend.service.implement;

import com.iberthy.backend.domain.entity.ProdutoModel;
import com.iberthy.backend.exception.GenericException;
import com.iberthy.backend.repository.ProdutoRepository;
import com.iberthy.backend.service.ProdutoService;
import com.iberthy.backend.util.CommonMethods;
import com.iberthy.backend.util.Message;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Log4j2
@Service
public class ProdutoServiceImplement implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoModel> findAll(ProdutoModel filtro){
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var matcher = ExampleMatcher.matching().withIgnoreCase()
                    .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

            var example = Example.of(filtro,matcher);

            var page = produtoRepository.findAll(example);

            log.warn("Executada com sucesso!");
            return page;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

    public ProdutoModel findById(Long id){
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
    public ProdutoModel save(ProdutoModel produto){
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
    public ProdutoModel edite(Long id, ProdutoModel produto){
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
    public Boolean delete(Long id){
        var nomeFunc = CommonMethods.getNameFunction();
        try {
            log.info("Iniciando execução da função {}", nomeFunc);

            var produtoDb = produtoRepository.findByIdActive(id);

            if(produtoDb == null){throw new GenericException(Message.produtoInvalidId);}

            produtoDb.setEnabled(false);

            produtoRepository.save(produtoDb);

            log.warn("Executada com sucesso!");
            return true;
        }catch(Exception ex){
            log.error("Executada com erro ".concat(ex.getMessage()), ex);
            throw ex;
        }finally {
            log.info("Finalizando execução da função {}", nomeFunc);
        }
    }

}
