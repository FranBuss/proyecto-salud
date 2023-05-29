package com.equipoUno.proyectoSalud.dto;

import com.equipoUno.proyectoSalud.entities.Appointment;
import com.equipoUno.proyectoSalud.enumerations.HealthInsurance;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PatientDTO {

    @NotNull(message = "Tiene que seleccionar una obra social válida.")
    @Enumerated(EnumType.STRING)
    private HealthInsurance healthInsurance;

    @Pattern(regexp = "^[0-9]{10,12}$", message = "Ingrese un celular válido.")
    private String contact;
    private Appointment appointment;


}
