package com.iberthy.backend.service.implement;

import com.iberthy.backend.domain.entity.Produto;
import com.iberthy.backend.exception.GenericException;
import com.iberthy.backend.repository.ProdutoRepository;
import com.iberthy.backend.service.ProdutoService;
import com.iberthy.backend.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoServiceImplement implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Page<Produto> findAll(Produto filtro, Pageable pageable){

        var matcher = ExampleMatcher.matching().withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(filtro,matcher);

        if(filtro.getAtivo() != false){
            filtro.setAtivo(true);
        }

        return produtoRepository.findAll(example, pageable);
    }

    public Produto findById(Long id){
        return produtoRepository.findByIdActive(id);
    }

    @Transactional
    public Produto save(Produto produto){
        return produtoRepository.save(produto);
    }

    @Transactional
    public Produto edite(Long id, Produto produto){
        var produtoDb = produtoRepository.findByIdActive(id);

        if(produtoDb == null){throw new GenericException(Message.produtoInvalidId);}

        produto.setId(produtoDb.getId());
        return this.save(produto);
    }

    @Transactional
    public Produto delete(Long id){
        var produtoDb = produtoRepository.findByIdActive(id);

        if(produtoDb == null){throw new GenericException(Message.produtoInvalidId);}

        produtoDb.setAtivo(false);
        return produtoRepository.save(produtoDb);
    }

}
