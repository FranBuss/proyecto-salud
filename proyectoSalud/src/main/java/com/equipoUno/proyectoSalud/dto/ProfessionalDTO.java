package com.equipoUno.proyectoSalud.dto;

import com.equipoUno.proyectoSalud.entities.Appointment;
import com.equipoUno.proyectoSalud.enumerations.Specialization;
import lombok.*;


import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalDTO{

    @NotNull(message = "Tiene que seleccionar una especialización válida.")
    @Enumerated(EnumType.STRING)
    private Specialization specialization;

    private LocalTime entryTime;

    private LocalTime exitTime;

    private float charge;

    public String getSpecializationName(){
        return specialization.getDisplayName();
    }

}







