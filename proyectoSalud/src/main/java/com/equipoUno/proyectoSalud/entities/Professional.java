package com.equipoUno.proyectoSalud.entities;

import com.equipoUno.proyectoSalud.enumerations.Specialization;
import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "professionals")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@SuperBuilder
@Data
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "id")
public class Professional extends User {

    @AttributeOverrides({
            @AttributeOverride(name = "id", column = @Column(name = "user_id")),
            @AttributeOverride(name = "name", column = @Column(name = "name")),
            @AttributeOverride(name = "email", column = @Column(name = "email")),
            @AttributeOverride(name = "password", column = @Column(name = "password")),
            @AttributeOverride(name = "rol", column = @Column(name = "rol")),
            @AttributeOverride(name = "createdAt", column = @Column(name = "created_at"))
    })

    @Column(name = "drop_out")
    private boolean dropOut = false;

    @Column(name = "specialization")
    @Enumerated(EnumType.STRING)
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

