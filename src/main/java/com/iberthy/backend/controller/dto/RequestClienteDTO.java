package com.iberthy.backend.controller.dto;

import com.iberthy.backend.controller.dto.abstracts.PessoaDTO;
import com.iberthy.backend.domain.entity.Cliente;
import com.iberthy.backend.domain.enums.Sexo;
import com.iberthy.backend.util.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestClienteDTO extends PessoaDTO {

    @NotNull(message = Message.saldoCarteiraNotNull)
    private Double saldoCarteira;

    public Cliente transformIntoCliente(RequestClienteDTO clienteDTO){

        var cliente = new Cliente();

        cliente.setNome( clienteDTO.getNome());
        cliente.setCpf( clienteDTO.getCpf());

        if(clienteDTO.getSexo() != null && !clienteDTO.getSexo().isEmpty()){
            cliente.setSexo(Sexo.valueOf(clienteDTO.getSexo()));
        }else{
            cliente.setSexo(null);
        }

        cliente.setTelefone( clienteDTO.getTelefone());
        cliente.setEmail( clienteDTO.getEmail());
        cliente.setSaldoCarteira( clienteDTO.getSaldoCarteira());

        return cliente;
    }
    
}
