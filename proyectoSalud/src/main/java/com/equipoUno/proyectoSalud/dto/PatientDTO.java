package com.equipoUno.proyectoSalud.dto;

import com.equipoUno.proyectoSalud.enumerations.HealthInsurance;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PatientDTO extends UserDTO {

    private HealthInsurance healthInsurance;
    private String contact;

}
