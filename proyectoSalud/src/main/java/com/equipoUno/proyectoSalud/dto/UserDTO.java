package com.equipoUno.proyectoSalud.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    private String id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;

}
