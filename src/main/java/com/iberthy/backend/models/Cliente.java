package com.iberthy.backend.models;

import com.iberthy.backend.models.abstracts.Pessoa;
import com.iberthy.backend.utils.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Cliente extends Pessoa {

    @NotNull(message = Message.saldoCarteiraNotNull)
    private Double saldoCarteira;

    private LocalDateTime dataDaPrimeiraCompra;

    private LocalDateTime dataDeCadastro;
}
