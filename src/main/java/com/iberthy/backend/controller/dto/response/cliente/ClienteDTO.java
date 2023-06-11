package com.iberthy.backend.controller.dto.response.cliente;
import com.iberthy.backend.controller.dto.abstracts.PessoaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDTO extends PessoaDTO {
    public Long id;
    private Double saldoCarteira;
}
