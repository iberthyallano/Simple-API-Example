package com.iberthy.backend.domain.entity;

import com.iberthy.backend.domain.abstracts.Pessoa;
import com.iberthy.backend.util.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Usuario extends Pessoa {

    @NotBlank(message = Message.loginNotBlank)
    private String login;

    @NotBlank(message = Message.senhaNotBlank)
    @Pattern(regexp = Message.senhaRegex, message = Message.senhaInvalidFormat)
    private String senha;

}
