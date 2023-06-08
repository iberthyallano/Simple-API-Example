package com.iberthy.backend.domain.validation.sexo;

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
@Constraint(validatedBy = SexoValidation.class)
public @interface SexoValidate {
    String message() default Message.sexoValidate;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
