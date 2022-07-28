package com.iberthy.backend.controller;

import com.iberthy.backend.domain.entity.Produto;
import com.iberthy.backend.service.ProdutoService;
import com.iberthy.backend.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public Page<Produto> listarProdutos(Produto filtro, Pageable pageable){
        return produtoService.findAll(filtro,pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto salvarProduto(@Valid @RequestBody Produto produto){
        return produtoService.save(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoById(@PathVariable Long id){
        var produtoDb = produtoService.findById(id);

        if(produtoDb == null){throw new ResponseStatusException(HttpStatus.NOT_FOUND, Message.produtoNotFoud);}

        return ResponseEntity.ok(produtoDb);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> editarProdutoById(@PathVariable Long id, @Valid @RequestBody Produto produto){
        return ResponseEntity.ok(produtoService.edite(id, produto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProdutoById(@PathVariable Long id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
