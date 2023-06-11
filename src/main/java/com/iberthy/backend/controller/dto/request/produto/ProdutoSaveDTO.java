package com.iberthy.backend.controller.dto.request.produto;

import com.iberthy.backend.util.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProdutoSaveDTO {
    @NotBlank(message = Message.nomeNotBlank)
    public String nome;

    @NotBlank(message = Message.descricaoNotBlank)
    private String descricao;

    @NotNull(message = Message.precoNotNull)
    @Min(value = 0, message = Message.precoMin)
    private Double preco;
}
