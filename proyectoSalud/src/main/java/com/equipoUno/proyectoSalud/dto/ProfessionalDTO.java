package com.equipoUno.proyectoSalud.dto;

import com.equipoUno.proyectoSalud.enumerations.Specialization;
import lombok.*;


import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalDTO{

    private Specialization specialization;
    private LocalTime entryTime;
    private LocalTime exitTime;
    private float charge;
    private float qualification;

    public String getSpecializationName(){
        return specialization.getDisplayName();
    }

}







