package com.equipoUno.proyectoSalud.anotations;

import java.util.Arrays;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidEmailValidator implements ConstraintValidator<ValidEmail, String> {

    private static final String REGEX = "^[a-zA-Z0-9._-]{6,30}$";
    private static final String[] VALID_DOMAINS = {
            "gmail.com",
            "gmail.com.ar",
            "outlook.com",
            "outlook.com.ar",
            "hotmail.com",
            "yahoo.com",
            "yahoo.com.ar",
    };

    @Override
    public void initialize(ValidEmail constraintAnnotation) {

    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email != null && !email.isEmpty()) {
            String[] arrParts = email.split("@");

            if (arrParts.length != 2 || !arrParts[0].matches(REGEX) || !isValidDomain(arrParts[1])) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Ingrese un email válido.")
                        .addConstraintViolation();
                return false;
            }
        } else {
            return false;
        }
        return true;
    }

    private boolean isValidDomain(String domain) {
        return Arrays.stream(VALID_DOMAINS).anyMatch(validDomain -> validDomain.equals(domain));
    }
}
