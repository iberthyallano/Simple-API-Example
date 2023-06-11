package com.iberthy.backend.controller.dto.request.cliente;

import com.iberthy.backend.controller.dto.abstracts.PessoaDTO;
import com.iberthy.backend.util.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteSaveDTO extends PessoaDTO {
    @NotNull(message = Message.saldoCarteiraNotNull)
    private Double saldoCarteira;
}
