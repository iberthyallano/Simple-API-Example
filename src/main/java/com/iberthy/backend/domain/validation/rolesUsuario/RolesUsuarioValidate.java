package com.iberthy.backend.domain.validation.rolesUsuario;

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
@Constraint(validatedBy = RolesUsuarioValidation.class)
public @interface RolesUsuarioValidate {
    String message() default Message.usuarioInvalidRoles;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
