package com.equipoUno.proyectoSalud.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "appointments")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
@Data
public class Appointment {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    protected String id;

    @ManyToOne
    @JoinColumn(name = "idPatient")
    private Patient patient;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProfessional")
    private Professional professional;

    @Column(name = "appointment")
    private LocalTime appointment;

    @Column(name = "day")
    private String day;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "duration")
    private int duration = 30;

    @Column(name = "state",  columnDefinition = "TINYINT", length = 1)
    private boolean state;

    @Column(name = "comments")
    private String comments;


}
