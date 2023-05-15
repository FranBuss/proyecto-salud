package com.equipoUno.proyectoSalud.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "patients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class Patient extends User{

    @Column(name = "health_insurance")
    private String healthInsurance;

    @Column(name = "contact")
    private String contact;


}
