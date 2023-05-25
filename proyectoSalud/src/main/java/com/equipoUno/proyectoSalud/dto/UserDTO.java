package com.equipoUno.proyectoSalud.dto;

import com.equipoUno.proyectoSalud.enumerations.EmailDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Size(min = 3, max = 15, message = "El nombre debe tener entre 3 y 15 caracteres.")
    private String name;

    @Size(min = 3, max = 15, message = "El apellido debe tener entre 3 y 15 caracteres.")
    private String surname;

    //Regex: Solo letras, números y (.,-,_)
    @Pattern(regexp = "^[A-Za-z0-9._-]+$", message = "El email solo puede contener -,_,.,letras y/o números. ")
    private String email;

    @NotNull(message = "Tiene que seleccionar un dominio válido.")
    @Enumerated(EnumType.STRING)
    private EmailDomain emailSuffix;

    @Min(value = 6,message = "La contraseña tiene que tener un mínimo de 6 caracteres.")
    @Max(value = 10, message = "La contraseña tiene que tener un máximo de 10 caracteres.")
    //Regex: Al menos una maýuscula, una minúscula, un número, un caracter especial y entre 6 y 10 caracteres.
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{6,10}$")
    private String password;

    private String confpassword;

    @AssertTrue(message = "Las contraseñas no coinciden.")
    public boolean isPasswordConfirmed(){
        return  password != null && password.equals(confpassword);
    }

}
