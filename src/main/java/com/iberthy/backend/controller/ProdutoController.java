package com.iberthy.backend.controller;

import com.iberthy.backend.controller.Mapper.ProdutoMapper;
import com.iberthy.backend.controller.dto.request.produto.ProdutoFilterDTO;
import com.iberthy.backend.controller.dto.request.produto.ProdutoSaveDTO;
import com.iberthy.backend.controller.dto.response.produto.ProdutoDTO;
import com.iberthy.backend.service.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@PreAuthorize("hasAnyAuthority('ADMINISTRADOR', 'FUNCIONARIO')")
@RequestMapping("/produtos")
@Api(tags = "Produto", description = " ")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ProdutoMapper produtoMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Criação de um produto")
    public ResponseEntity<ProdutoDTO> salvarProduto(@Valid @RequestBody ProdutoSaveDTO produto){
        var model = produtoMapper.mapProdutoSaveDTOToModel(produto);
        var response = produtoMapper.mapProdutoModelToProdutoDTO(produtoService.save(model));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualização de dados de um produto específico por ID")
    public ResponseEntity<ProdutoDTO> editarProdutoById(@PathVariable Long id, @Valid @RequestBody ProdutoSaveDTO produto){
        var model = produtoMapper.mapProdutoSaveDTOToModel(produto);
        var response = produtoMapper.mapProdutoModelToProdutoDTO(produtoService.edite(id, model));
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @ApiOperation("Listagem de produtos")
    public ResponseEntity<List<ProdutoDTO>> listarProdutos(ProdutoFilterDTO filtro){
        var model = produtoMapper.mapProdutoFilterDTOToModel(filtro);
        var response = produtoMapper.mapProdutoModelToProdutoDTO(produtoService.findAll(model));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Exclusão de dados de um produto específico por ID")
    public ResponseEntity<Boolean> deleteProdutoById(@PathVariable Long id){
        var response = produtoService.delete(id);
        return ResponseEntity.ok(response);
    }

}
