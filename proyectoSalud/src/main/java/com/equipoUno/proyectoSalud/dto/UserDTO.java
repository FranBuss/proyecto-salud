package com.equipoUno.proyectoSalud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class UserDTO {

    private String name;
    private String email;
    private String password;

}
