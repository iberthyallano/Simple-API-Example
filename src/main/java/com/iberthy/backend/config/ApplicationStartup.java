package com.iberthy.backend.config;

import com.iberthy.backend.controller.dto.RequestUsuarioDTO;
import com.iberthy.backend.domain.enums.TipoRoles;
import com.iberthy.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        var apiadm = usuarioService.finByLogin("apiadm");

        if(apiadm == null){

            var roles = Arrays.asList(TipoRoles.ADMINISTRADOR.name());

            var userDTO = new RequestUsuarioDTO("apiadm", "P@sw12", roles);

            userDTO.setNome("Administrador");
            userDTO.setCpf("000.000.000-00");
            userDTO.setSexo("NAO_IDENTIFICADO");
            userDTO.setTelefone("00 00000-0000");
            userDTO.setEmail("apiadm@simple_api_example.com");

            usuarioService.save(userDTO);

            System.out.println("Cadastrei o usuario básico da API");
        }

    }

}
