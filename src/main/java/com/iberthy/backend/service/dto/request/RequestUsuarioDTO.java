package com.iberthy.backend.service.dto.request;

import com.iberthy.backend.service.dto.abstracts.PessoaDTO;
import com.iberthy.backend.domain.entity.Usuario;
import com.iberthy.backend.domain.enums.Sexo;
import com.iberthy.backend.util.Message;
import com.iberthy.backend.domain.validation.rolesUsuario.RolesUsuarioValidate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestUsuarioDTO extends PessoaDTO {

    @NotBlank(message = Message.userNameNotBlank)
    private String username;

    @NotBlank(message = Message.passwordNotBlank)
    @Pattern(regexp = Message.passwordRegex, message = Message.passwordInvalidFormat)
    private String password;

    @RolesUsuarioValidate
    private List<String> roles;

    public Usuario transformIntoUsuario(RequestUsuarioDTO usuarioDTO){

        var usuario = new Usuario();

        usuario.setNome( usuarioDTO.getNome());
        usuario.setCpf( usuarioDTO.getCpf());

        if(usuarioDTO.getSexo() != null && !usuarioDTO.getSexo().isEmpty()){
            usuario.setSexo(Sexo.valueOf(usuarioDTO.getSexo()));
        }else{
            usuario.setSexo(null);
        }

        usuario.setTelefone( usuarioDTO.getTelefone());
        usuario.setEmail( usuarioDTO.getEmail());
        usuario.setUsername(usuarioDTO.getUsername());
        usuario.setPassword(usuarioDTO.getPassword());
        usuario.setRoles(usuarioDTO.getRoles());

        return usuario;
    }

}