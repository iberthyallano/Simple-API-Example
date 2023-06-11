package com.iberthy.backend.controller.dto.request.usuario;

import com.iberthy.backend.util.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioUpdSenhaDTO {
    @NotBlank(message = Message.passwordNotBlank)
    @Pattern(regexp = Message.passwordRegex, message = Message.passwordInvalidFormat)
    private String password;
}
