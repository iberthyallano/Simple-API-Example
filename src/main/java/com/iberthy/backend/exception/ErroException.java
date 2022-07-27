package com.iberthy.backend.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErroException {
    private Integer status;
    private LocalDateTime dataHora;
    private String titulo;
    private List<Campo> campos;

    @AllArgsConstructor
    @Getter
    public static class Campo{
        private String nome;
        private String mensagem;
    }
}
