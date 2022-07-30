package com.iberthy.backend.domain.abstracts;

import com.iberthy.backend.domain.enums.Sexo;
import com.iberthy.backend.util.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Pessoa extends AbstractEntity {

    @NotBlank(message = Message.nomeNotBlank)
    public String nome;

    @NotBlank(message = Message.cpfNotBlank)
    @Pattern(regexp = Message.cpfRegex, message = Message.cpfInvalidFormat)
    private String cpf;

    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @NotBlank(message = Message.telefoneNotBlank)
    private String telefone;

    @NotBlank(message = Message.emailNotBlank)
    @Email(message = Message.emailInvalidFormat)
    private String email;

}
