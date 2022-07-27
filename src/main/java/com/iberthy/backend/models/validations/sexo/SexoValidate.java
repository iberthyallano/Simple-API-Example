package com.iberthy.backend.models.validations.sexo;

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
    String message() default "O sexo informado não corresponde aos aceitos pela API! [Feminino(F), Masculino(M), Não informado(N)]";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
