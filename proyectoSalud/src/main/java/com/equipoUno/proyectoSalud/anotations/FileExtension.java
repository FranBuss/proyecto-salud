package com.equipoUno.proyectoSalud.anotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FileExtensionValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileExtension {
    String[] value();
    String message() default "Invalid file extension";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}