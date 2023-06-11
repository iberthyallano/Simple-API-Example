package com.iberthy.backend.controller.dto.request.usuario;

import com.iberthy.backend.controller.dto.abstracts.PessoaDTO;
import com.iberthy.backend.domain.validation.rolesUsuario.RolesUsuarioValidate;
import com.iberthy.backend.util.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioSaveDTO extends PessoaDTO {
    @NotBlank(message = Message.userNameNotBlank)
    private String username;

    @NotBlank(message = Message.passwordNotBlank)
    @Pattern(regexp = Message.passwordRegex, message = Message.passwordInvalidFormat)
    private String password;

    @RolesUsuarioValidate
    private List<String> roles;
}
