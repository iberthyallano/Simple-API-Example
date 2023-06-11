package com.iberthy.backend.controller.dto.response.produto;
import com.iberthy.backend.util.Message;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class ProdutoDTO {
    @NotNull
    private Long id;

    @NotBlank(message = Message.nomeNotBlank)
    private String nome;

    @NotBlank(message = Message.descricaoNotBlank)
    private String descricao;

    @NotNull(message = Message.precoNotNull)
    @Min(value = 0, message = Message.precoMin)
    private Double preco;
}
