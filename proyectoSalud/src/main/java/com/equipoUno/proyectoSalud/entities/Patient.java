package com.equipoUno.proyectoSalud.entities;

import com.equipoUno.proyectoSalud.enumerations.HealthInsurance;
import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;


@Entity
@Table(name = "patient")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
public class Patient extends User{

    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "patient_id")),
            @AttributeOverride(name = "name", column = @Column(name = "patient_name")),
            @AttributeOverride(name = "email", column = @Column(name = "patient_email")),
            @AttributeOverride(name = "password", column = @Column(name = "patient_password")),
            @AttributeOverride(name = "rol", column = @Column(name = "rol")),
            @AttributeOverride(name = "createdAt", column = @Column(name = "created_at"))
    })

    @Column(name = "health_insurance")
    @Enumerated(EnumType.STRING)
    private HealthInsurance healthInsurance;

    @Column(name = "contact")
    private String contact;


}
