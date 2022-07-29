package com.iberthy.backend.service.implement;

import com.iberthy.backend.domain.entity.Role;
import com.iberthy.backend.exception.GenericException;
import com.iberthy.backend.repository.RoleRepository;
import com.iberthy.backend.service.RoleService;
import com.iberthy.backend.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImplement implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).get();
    }

    @Override
    public Role findByNome(String nome) {
        return roleRepository.findByNome(nome);
    }

    @Override
    @Transactional
    public Role save(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> convertListStringInListRole(List<String> rolesName) {

        List<Role> retorno = new ArrayList<>();

        if(rolesName != null && !rolesName.isEmpty()){

            for (String role : rolesName) {

                var roleDb = roleRepository.findByNome(role);

                if(roleDb == null){throw new GenericException(Message.usuarioInvalidRoles);}

                retorno.add(roleDb);

            }
        }

        return retorno;
    }

}
