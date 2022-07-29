package com.iberthy.backend.validation.statusPedido;

import com.iberthy.backend.domain.enums.StatusPedido;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class StatusPedidoValidation implements ConstraintValidator<StatusPedidoValidate, String> {

    @Override
    public void initialize(StatusPedidoValidate constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String status, ConstraintValidatorContext constraintValidatorContext) {

        if(status == null){
            return false;
        }else if(!status.equals(StatusPedido.REALIZADO.name()) && !status.equals(StatusPedido.CANCELADO.name())){
            return false;
        }

        return true;
    }

}
