package com.equipoUno.proyectoSalud.entities;

import com.equipoUno.proyectoSalud.enumerations.HealthInsurance;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "patients")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Patient {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "appointment_id")
    private Appointment appointment;

    @Column(name = "health_insurance")
    @Enumerated(EnumType.STRING)
    private HealthInsurance healthInsurance;

    @Column(name = "contact")
    private String contact;

    @OneToOne
    @JoinColumn(name = "clinic_history_id")
    private ClinicHistory clinicHistory;

}
