package com.iberthy.backend.controller.dto.request.cliente;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteFilterDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String sexo;
    private String email;
    private Boolean enabled;
}
