package com.iberthy.backend.service;

import com.iberthy.backend.domain.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoService {

    Page<Produto> findAll(Produto filtro, Pageable pageable);

    Produto findById(Long id);

    Produto save(Produto produto);

    Produto edite(Long id, Produto produto);

    Produto delete(Long id);

}
