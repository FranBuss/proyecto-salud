package com.equipoUno.proyectoSalud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalDTO {

    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String name;
    private String specialization;
    private String email;
    private LocalTime entryTime;
    private LocalTime exitTime;
}







