package com.iberthy.backend.config;

import com.iberthy.backend.controller.dto.RequestUsuarioDTO;
import com.iberthy.backend.domain.entity.Role;
import com.iberthy.backend.domain.enums.TipoRoles;
import com.iberthy.backend.service.RoleService;
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

    @Autowired
    private RoleService roleService;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {

        var listRolesDb = roleService.findAll();
        var enumRoles = TipoRoles.values();

        if(listRolesDb.size() < enumRoles.length){
            for (TipoRoles tipoRole : enumRoles) {
                var roleDb = roleService.findByNome(tipoRole.name());

                if(roleDb == null){roleService.save(new Role(tipoRole.name()));}
            }
            System.out.println("Cadastrei as roles");
        }

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
