package com.iberthy.backend.service;

import com.iberthy.backend.domain.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(Long id);

    Role findByNome(String nome);

    Role save(Role nome);

    List<Role> convertListStringInListRole( List<String> rolesName);

}
