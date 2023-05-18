package com.equipoUno.proyectoSalud.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PatientDTO extends UserDTO {

    private String healthInsurance;
    private String contact;

}
