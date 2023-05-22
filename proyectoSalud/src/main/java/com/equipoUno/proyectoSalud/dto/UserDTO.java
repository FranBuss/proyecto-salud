package com.equipoUno.proyectoSalud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserDTO {

    private String name;

    private String email;

    private String password;

    private List<String> roles;

    private String confpassword;

}
