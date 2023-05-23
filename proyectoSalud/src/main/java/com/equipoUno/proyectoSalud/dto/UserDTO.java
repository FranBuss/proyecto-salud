package com.equipoUno.proyectoSalud.dto;

import com.equipoUno.proyectoSalud.enumerations.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String name;

    private String email;

    private String emailSuffix;

    private String password;

    private String confpassword;

}
