package com.equipoUno.proyectoSalud.anotations;

import com.equipoUno.proyectoSalud.entities.User;
import com.equipoUno.proyectoSalud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailAlreadyExistValidator implements ConstraintValidator<EmailAlreadyExist, String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public void initialize(EmailAlreadyExist constraintAnnotation) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {

        if (email != null && !email.isEmpty()) {

            try {
                User userDB = userRepository.findByEmail(email);

                if (userDB != null) {
                    context.disableDefaultConstraintViolation();
                    context.buildConstraintViolationWithTemplate("El email ya está registrado.")
                            .addConstraintViolation();
                    return false;
                }

            } catch (DataAccessException e) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Error 500 al buscar el correo.")
                        .addConstraintViolation();
                return false;
            }

        } else {
            return false;
        }

        return true;
    }

}
