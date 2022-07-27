package com.iberthy.backend.domain.entity;

import com.iberthy.backend.domain.abstracts.AbstractEntity;
import com.iberthy.backend.utils.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Produto extends AbstractEntity {

    @NotBlank(message = Message.nomeNotBlank)
    public String nome;

    @NotBlank(message = Message.descricaoNotBlank)
    private String descricao;

    @NotNull(message = Message.precoNotNull)
    @Min(value = 0, message = Message.precoMin)
    private Double preco;

}
