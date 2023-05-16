package com.equipoUno.proyectoSalud.entities;

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
@PrimaryKeyJoinColumn(name = "id")
public class Patient extends User{

    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "user_id")),
            @AttributeOverride(name = "name", column = @Column(name = "name")),
            @AttributeOverride(name = "email", column = @Column(name = "email")),
            @AttributeOverride(name = "password", column = @Column(name = "password")),
            @AttributeOverride(name = "rol", column = @Column(name = "rol")),
            @AttributeOverride(name = "createdAt", column = @Column(name = "created_at"))
    })

    @Column(name = "health_insurance")
    private String healthInsurance;

    @Column(name = "contact")
    private String contact;


}
