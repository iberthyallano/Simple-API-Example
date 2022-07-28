package com.iberthy.backend.controller.dto;

import com.iberthy.backend.domain.enums.Sexo;
import com.iberthy.backend.utils.Message;
import com.iberthy.backend.validation.sexo.SexoValidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PessoaDTO {

    @NotBlank(message = Message.nomeNotBlank)
    public String nome;

    @NotBlank(message = Message.cpfNotBlank)
    @Pattern(regexp = Message.cpfRegex, message = Message.cpfInvalidFormat)
    private String cpf;

    @SexoValidate
    private String sexo;

    @NotBlank(message = Message.telefoneNotBlank)
    private String telefone;

    @NotBlank(message = Message.emailNotBlank)
    @Email(message = Message.emailInvalidFormat)
    private String email;
    
}
