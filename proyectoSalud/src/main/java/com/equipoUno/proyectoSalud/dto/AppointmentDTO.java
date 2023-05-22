package com.equipoUno.proyectoSalud.dto;

import com.equipoUno.proyectoSalud.entities.Patient;
import com.equipoUno.proyectoSalud.entities.Professional;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class AppointmentDTO {

    private Patient patient;
    private Professional professional;
    private LocalDateTime appointment;
    private int duration;
    private String state;
    private String comments;



}
