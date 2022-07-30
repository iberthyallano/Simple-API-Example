package com.iberthy.backend.controller.dto.response;

import com.iberthy.backend.controller.dto.abstracts.PessoaDTO;
import com.iberthy.backend.domain.entity.Usuario;
import com.iberthy.backend.domain.enums.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;
import java.util.List;

@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseUsuarioDTO extends PessoaDTO {

    private Long id;
    private String username;
    private String password;
    private List<String> roles;


    public ResponseUsuarioDTO(Usuario usuario){
        this.id = usuario.getId();
        this.username = usuario.getUsername();
        this.password = usuario.getPassword();
        this.roles = usuario.getRoles();
        this.setNome(usuario.getNome());
        this.setCpf(usuario.getCpf());
        this.setSexo(usuario.getSexo().name());
        this.setTelefone(usuario.getTelefone());
        this.setEmail(usuario.getEmail());
    }
}