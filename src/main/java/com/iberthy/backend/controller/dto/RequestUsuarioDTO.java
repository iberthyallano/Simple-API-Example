package com.iberthy.backend.controller.dto;

import com.iberthy.backend.domain.entity.Usuario;
import com.iberthy.backend.domain.enums.Sexo;
import com.iberthy.backend.utils.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestUsuarioDTO extends PessoaDTO {

    @NotBlank(message = Message.loginNotBlank)
    private String login;

    @NotBlank(message = Message.senhaNotBlank)
    @Pattern(regexp = Message.senhaRegex, message = Message.senhaInvalidFormat)
    private String senha;

    public Usuario transformIntoUsuario(RequestUsuarioDTO usuarioDTO){

        var usuario = new Usuario();

        usuario.setNome( usuarioDTO.getNome());
        usuario.setCpf( usuarioDTO.getCpf());
        usuario.setSexo(Sexo.valueOf(usuarioDTO.getSexo()));
        usuario.setTelefone( usuarioDTO.getTelefone());
        usuario.setEmail( usuarioDTO.getEmail());
        usuario.setLogin(usuarioDTO.getLogin());
        usuario.setSenha(usuarioDTO.getSenha());

        return usuario;
    }
}