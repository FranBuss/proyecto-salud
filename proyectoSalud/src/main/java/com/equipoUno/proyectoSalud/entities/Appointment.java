package com.equipoUno.proyectoSalud.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.DayOfWeek;
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

    @ManyToOne
    @JoinColumn(name = "idProfessional")
    private Professional professional;

    @Column(name = "appointment")
    private LocalDateTime appointment;

    @Column(name = "duration")
    private int duration = 30;

    @Column(name = "state")
    private String state = "disponible";

    @Column(name = "comments")
    private String comments;



    @ElementCollection
    @Column(name = "available_day")
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> availableDays = new HashSet<>(Arrays.asList(
            DayOfWeek.MONDAY,
            DayOfWeek.TUESDAY,
            DayOfWeek.WEDNESDAY,
            DayOfWeek.THURSDAY,
            DayOfWeek.FRIDAY
    ));

}
