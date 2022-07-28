package com.iberthy.backend.validation.StatusPedido;

import com.iberthy.backend.util.Message;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target( { FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = StatusPedidoValidation.class)
public @interface StatusPedidoValidate {
    String message() default Message.statusPedidoValidate;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
