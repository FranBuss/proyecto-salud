package com.equipoUno.proyectoSalud.dto;

import com.equipoUno.proyectoSalud.entities.Patient;
import com.equipoUno.proyectoSalud.entities.Professional;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AppointmentDTO {

    private Patient patient;
    private Professional professional;
    private LocalDateTime appointment;
    private int duration;
    private String state;
    private String comments;

    private LocalTime startTime;

    private LocalTime endTime;

    private Set<DayOfWeek> availableDays;

}
