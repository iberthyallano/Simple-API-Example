package com.iberthy.backend.controller.dto.request.produto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdutoFilterDTO {
    private Long id;
    private String nome;
}
