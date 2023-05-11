package com.equipoUno.proyectoSalud.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "patient")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Patient extends User{

    @Column(name = "health_insurance")
    private String healthInsurance;

    @Column(name = "contact")
    private String contact;

}
