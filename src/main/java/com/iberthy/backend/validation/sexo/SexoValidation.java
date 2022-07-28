package com.iberthy.backend.validation.sexo;

import com.iberthy.backend.domain.enums.Sexo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SexoValidation implements ConstraintValidator<SexoValidate, String> {
    @Override
    public void initialize(SexoValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String sexo, ConstraintValidatorContext constraintValidatorContext) {

        if(sexo == null){
            return false;
        }else if(!sexo.equals(Sexo.FEMININO.name()) && !sexo.equals(Sexo.MASCULINO.name()) && !sexo.equals(Sexo.NAO_IDENTIFICADO.name())){
            return false;
        }

        return true;
    }
}
