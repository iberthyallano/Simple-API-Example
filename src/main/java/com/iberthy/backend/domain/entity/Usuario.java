package com.iberthy.backend.domain.entity;

import com.iberthy.backend.domain.abstracts.Pessoa;
import com.iberthy.backend.util.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Usuario extends Pessoa {

    @NotBlank(message = Message.loginNotBlank)
    private String login;

    @NotBlank(message = Message.senhaNotBlank)
    private String senha;

}
