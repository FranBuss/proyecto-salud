package com.equipoUno.proyectoSalud.entities;

import com.equipoUno.proyectoSalud.enumerations.Specialization;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "professionals")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Professional {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProfessional")
    private List<Appointment> appointment;

    @Column(name = "drop_out")
    private boolean dropOut = true;

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
