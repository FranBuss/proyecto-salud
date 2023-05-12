package com.equipoUno.proyectoSalud.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "professional")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
public class Professional extends User{

    @Column(name = "drop_out")
    private boolean dropOut = false;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "entry_time")
    private LocalTime entryTime;

    @Column(name = "exit_time")
    private LocalTime exitTime;

    @Column(name = "charge")
    private float charge;

    @Column(name = "qualification")
    private float qualification = 0f;

}
