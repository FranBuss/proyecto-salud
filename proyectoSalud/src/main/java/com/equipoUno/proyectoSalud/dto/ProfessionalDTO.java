package com.equipoUno.proyectoSalud.dto;

import com.equipoUno.proyectoSalud.enumerations.Specialization;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class ProfessionalDTO extends UserDTO{

    private Specialization specialization;
    private LocalTime entryTime;
    private LocalTime exitTime;
    private float charge;


    public String getSpecializationName(){
        return specialization.getDisplayName();
    }

}







