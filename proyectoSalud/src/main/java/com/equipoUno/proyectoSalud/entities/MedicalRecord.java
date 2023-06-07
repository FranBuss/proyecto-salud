package com.equipoUno.proyectoSalud.entities;

import com.equipoUno.proyectoSalud.enumerations.BloodType;
import com.equipoUno.proyectoSalud.enumerations.Gender;
import com.equipoUno.proyectoSalud.enumerations.HealthInsurance;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "medical_records")
public class MedicalRecord {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "idPatient")
    private Patient patient;

    @Column(name="patient_name")
    private String patientName;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "health_insurance")
    @Enumerated(EnumType.STRING)
    private HealthInsurance healthInsurance;

    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "blood_type")
    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    private String observations;

}

