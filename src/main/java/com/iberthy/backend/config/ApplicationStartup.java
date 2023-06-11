package com.iberthy.backend.config;

import com.iberthy.backend.domain.entity.UsuarioModel;
import com.iberthy.backend.domain.enums.Sexo;
import com.iberthy.backend.domain.enums.RolesUsuario;
import com.iberthy.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        var apiadm = usuarioService.finByUsername("apiadm");

        if(apiadm == null){

            var usuario = new UsuarioModel();

            usuario.setUsername("apiadm");
            usuario.setPassword("P@sw12");
            usuario.setRoles(List.of(RolesUsuario.ADMINISTRADOR.name()));
            usuario.setNome("Administrador");
            usuario.setCpf("000.000.000-00");
            usuario.setSexo(Sexo.NAO_IDENTIFICADO);
            usuario.setTelefone("00 00000-0000");
            usuario.setEmail("apiadm@simple_api_example.com");

            usuarioService.save(usuario);

            System.out.println("Cadastrei o usuario básico da API");
        }

    }

}
