package com.iberthy.backend.service;

import com.iberthy.backend.domain.entity.ProdutoModel;

import java.util.List;

public interface ProdutoService {

    List<ProdutoModel> findAll(ProdutoModel filtro);

    ProdutoModel findById(Long id);

    ProdutoModel save(ProdutoModel produto);

    ProdutoModel edite(Long id, ProdutoModel produto);

    Boolean delete(Long id);

}
