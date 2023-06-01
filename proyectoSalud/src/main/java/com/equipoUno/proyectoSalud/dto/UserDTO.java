package com.equipoUno.proyectoSalud.dto;

import com.equipoUno.proyectoSalud.anotations.FileExtension;
import com.equipoUno.proyectoSalud.enumerations.EmailDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Pattern(regexp = "^[a-zA-Z]{3,13}$", message = "El nombre debe tener entre 3 y 15 caracteres.")
    private String name;

    @Pattern(regexp = "^[a-zA-Z]{3,13}$", message = "El apellido debe tener entre 3 y 15 caracteres.")
    private String surname;

    // Regex: Solo letras, números y (.,-,_)
    @Pattern(regexp = "^[a-zA-Z0-9._-]{6,30}$", message = "El email solo puede contener -,_,.,letras y/o números.")
    private String email;

    @NotNull(message = "Tiene que seleccionar un dominio válido.")
    @Enumerated(EnumType.STRING)
    private EmailDomain emailSuffix;

    // Regex: Al menos una maýuscula, una minúscula, un número, un caracter especial
    // y entre 6 y 10 caracteres.
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{6,10}$", message = "La contraseña debe tener al menos una mayúscula, una minuscula, un número, un caracter especial y tener entre 6 y 10 caracteres.")
    private String password;

    private String confpassword;

    @FileExtension(value = { "jpg", "jpeg" }, message = "Solo se admiten imágenes jpg o jpeg")
    private MultipartFile imageFile;

    @AssertTrue(message = "Las contraseñas no coinciden.")
    public boolean isPasswordConfirmed() {
        return password != null && password.equals(confpassword);
    }

}
