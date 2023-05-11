package com.equipoUno.proyectoSalud.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class ProfessionalDTO extends UserDTO{

    private String specialization;
    private LocalTime entryTime;
    private LocalTime exitTime;
    private float charge;

}







