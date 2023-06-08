package com.equipoUno.proyectoSalud.dto;

import com.equipoUno.proyectoSalud.enumerations.BloodType;
import com.equipoUno.proyectoSalud.enumerations.Gender;
import com.equipoUno.proyectoSalud.enumerations.HealthInsurance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicalRecordDTO {

    private String patientName;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private HealthInsurance healthInsurance;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    private String observations;

    private float height;

    private float weight;
}
