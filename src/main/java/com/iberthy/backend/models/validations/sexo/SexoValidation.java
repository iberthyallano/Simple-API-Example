package com.iberthy.backend.models.validations.sexo;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SexoValidation implements ConstraintValidator<SexoValidate, Character> {
    @Override
    public void initialize(SexoValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Character sexo, ConstraintValidatorContext constraintValidatorContext) {

        if(sexo == null || !sexo.equals('F') || !sexo.equals('M') || !sexo.equals('N')){return false;}

        return true;
    }
}
