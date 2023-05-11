package com.equipoUno.proyectoSalud.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PatientDTO extends UserDTO {

    private String healthInsurance;
    private String contact;

}
