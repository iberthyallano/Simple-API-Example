package com.iberthy.backend.controller;

import com.iberthy.backend.domain.entity.Produto;
import com.iberthy.backend.service.ProdutoService;
import com.iberthy.backend.util.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'FUNCIONARIO')")
@RequestMapping("/produtos")
@Api(tags = "Produto", description = " ")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    @ApiOperation("Listagem de produtos")
    public Page<Produto> listarProdutos(Produto filtro, Pageable pageable){
        return produtoService.findAll(filtro,pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Criação de um produto")
    public Produto salvarProduto(@Valid @RequestBody Produto produto){
        return produtoService.save(produto);
    }

    @GetMapping("/{id}")
    @ApiOperation("Busca de um produto específico por ID")
    public ResponseEntity<Produto> buscarProdutoById(@PathVariable Long id){
        var produtoDb = produtoService.findById(id);

        if(produtoDb == null){throw new ResponseStatusException(HttpStatus.NOT_FOUND, Message.produtoNotFoud);}

        return ResponseEntity.ok(produtoDb);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualização de dados de um produto específico por ID")
    public ResponseEntity<Produto> editarProdutoById(@PathVariable Long id, @Valid @RequestBody Produto produto){
        return ResponseEntity.ok(produtoService.edite(id, produto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Exclusão de dados de um produto específico por ID")
    public ResponseEntity<Void> deleteProdutoById(@PathVariable Long id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
