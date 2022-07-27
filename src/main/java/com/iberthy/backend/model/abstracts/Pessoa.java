package com.iberthy.backend.model.abstracts;

import com.iberthy.backend.model.validations.sexo.SexoValidate;
import com.iberthy.backend.utils.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @NotBlank(message = Message.nomeNotBlank)
    public String nome;

    @NotBlank(message = Message.cpfNotBlank)
    @Pattern(regexp = "(^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$)|(^\\d{2}\\.\\d{3}\\.\\d{3}\\/\\d{4}\\-\\d{2}$)", message = Message.cpfInvalidFormat)
    private String cpf;

    @SexoValidate
    private Character sexo;

    @NotBlank(message = Message.telefoneNotBlank)
    private String telefone;

    @NotBlank(message = Message.emailNotBlank)
    @Email(message = Message.emailInvalidFormat)
    private String email;

    private Boolean ativo = true;
}
