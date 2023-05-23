package com.equipoUno.proyectoSalud.dto;

import com.equipoUno.proyectoSalud.enumerations.HealthInsurance;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PatientDTO {

    private HealthInsurance healthInsurance;
    private String contact;

}
