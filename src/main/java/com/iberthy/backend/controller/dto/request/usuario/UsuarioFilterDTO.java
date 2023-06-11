package com.iberthy.backend.controller.dto.request.usuario;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioFilterDTO {
    private Long id;
    private String nome;
    private String cpf;
    private String sexo;
    private String email;
    private Boolean enabled;
    private String username;
    private List<String> roles;
}
