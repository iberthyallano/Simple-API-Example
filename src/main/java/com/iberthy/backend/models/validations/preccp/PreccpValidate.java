package com.iberthy.backend.models.validations.preccp;

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
@Constraint(validatedBy = PreccpValidation.class)
public @interface PreccpValidate {
    String message() default "O preccp deve conter apenas números!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
