package com.equipoUno.proyectoSalud.dto;

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

    private String specialization;
    private LocalTime entryTime;
    private LocalTime exitTime;
    private float charge;

}







