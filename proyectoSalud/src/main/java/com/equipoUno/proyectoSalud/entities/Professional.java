package com.equipoUno.proyectoSalud.entities;

import com.equipoUno.proyectoSalud.enumerations.Specialization;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "professional")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class Professional extends User {

    @Column(name = "drop_out")
    private boolean dropOut = false;

    @Column(name = "specialization")
    private Specialization specialization;

    @Column(name = "entry_time")
    private LocalTime entryTime;

    @Column(name = "exit_time")
    private LocalTime exitTime;

    @Column(name = "charge")
    private float charge;

    @Column(name = "qualification")
    private float qualification = 0f;
}

