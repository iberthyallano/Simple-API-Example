package com.iberthy.backend.services;

import com.iberthy.backend.models.Funcionario;
import com.iberthy.backend.repositorys.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> findAll(){
        return funcionarioRepository.findAllActive();
    }

    public Funcionario findById(Long id){
        return funcionarioRepository.findByIdActive(id);
    }

    public Funcionario findByNome(String nome){
        return funcionarioRepository.findByNomeContaining(nome);
    }

    @Transactional
    public Funcionario save(Funcionario funcionario){
        return funcionarioRepository.save(funcionario);
    }

    @Transactional
    public Funcionario edite(Long id, Funcionario funcionario){
        var funcionarioDb = funcionarioRepository.findByIdActive(id);

        if(funcionarioDb != null){
            funcionario.setId(funcionarioDb.getId());
            return this.save(funcionario);
        }

        return null;
    }

    @Transactional
    public Funcionario delete(Long id){
        var funcionarioDb = funcionarioRepository.findByIdActive(id);

        if(funcionarioDb != null){
            funcionarioDb.setAtivo(false);
            return funcionarioRepository.save(funcionarioDb);
        }

        return null;
    }

}
