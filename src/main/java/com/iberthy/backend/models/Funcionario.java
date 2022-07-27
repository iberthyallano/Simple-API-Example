package com.iberthy.backend.models;

import com.iberthy.backend.models.abstracts.Pessoa;
import com.iberthy.backend.utils.Message;
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
public class Funcionario extends Pessoa {

    @NotBlank(message = Message.loginNotBlank)
    private String login;

    @NotBlank(message = Message.senhaNotBlank)
    @Pattern(regexp = "(?=^.{6,}$)((?=.*\\w)(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[|!\"$%&\\/\\(\\)\\?\\^\\'\\\\\\+\\-\\*]))^.*", message = Message.senhaInvalidFormat)
    private String senha;

}
