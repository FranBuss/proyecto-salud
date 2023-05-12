package com.equipoUno.proyectoSalud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class PatientDTO extends UserDTO {

    private String healthInsurance;
    private String contact;

}
