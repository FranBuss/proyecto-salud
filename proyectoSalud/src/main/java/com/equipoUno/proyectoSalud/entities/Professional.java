package com.equipoUno.proyectoSalud.entities;

import com.equipoUno.proyectoSalud.enumerations.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "professional")
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Professional extends User{

    @Column(name = "drop_out")
    private boolean dropOut;

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "entry_time")
    private LocalTime entryTime;

    @Column(name = "exit_time")
    private LocalTime exitTime;



}
