package com.iberthy.backend.models.validations.preccp;


import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PreccpValidation implements ConstraintValidator<PreccpValidate, String> {
    @Override
    public void initialize(PreccpValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String preccp, ConstraintValidatorContext constraintValidatorContext) {

        if( preccp != null && !preccp.isBlank() && !preccp.isEmpty()){
            if(!preccp.matches("^[\\$]?[-+]?[\\d\\.,]*[\\.,]?\\d+$")){
                return false;
            }
        }

        return true;
    }
}
