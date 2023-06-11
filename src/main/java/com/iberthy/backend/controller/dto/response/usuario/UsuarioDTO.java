package com.iberthy.backend.controller.dto.response.usuario;
import com.iberthy.backend.controller.dto.abstracts.PessoaDTO;
import com.iberthy.backend.domain.validation.rolesUsuario.RolesUsuarioValidate;
import com.iberthy.backend.util.Message;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


public class UsuarioDTO extends PessoaDTO {
    @NotNull
    public Long id;

    @NotBlank(message = Message.userNameNotBlank)
    private String username;

    @ElementCollection(fetch = FetchType.EAGER)
    @RolesUsuarioValidate
    private List<String> roles;
}
