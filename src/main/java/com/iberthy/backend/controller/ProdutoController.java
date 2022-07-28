package com.iberthy.backend.controller;

import com.iberthy.backend.domain.entity.Produto;
import com.iberthy.backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public Produto salvarProduto(@RequestBody Produto produto){
        return produtoService.save(produto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoById(@PathVariable Long id){
        var produtoDb = produtoService.findById(id);
        return produtoDb != null ? ResponseEntity.ok(produtoDb) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> editarProdutoById(@PathVariable Long id, @RequestBody Produto produto){
        var produtoDb= produtoService.edite(id, produto);
        return produtoDb != null ? ResponseEntity.ok(produtoDb) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProdutoById(@PathVariable Long id){
        var produtoDb= produtoService.delete(id);
        return produtoDb != null ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

}
