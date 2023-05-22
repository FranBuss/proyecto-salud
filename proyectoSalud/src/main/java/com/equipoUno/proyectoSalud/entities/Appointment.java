//package com.equipoUno.proyectoSalud.entities;
//
//import com.fasterxml.jackson.annotation.JsonIgnore;
//import lombok.*;
//import lombok.experimental.SuperBuilder;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//import java.time.LocalDateTime;
//import java.util.Date;
//
//@Entity
//@Table(name = "appointments")
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@SuperBuilder
//@Data
//public class Appointment {
//
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
//    @Column(name = "id")
//    protected String id;
//
//    @ManyToOne
//    @JoinColumn(name = "idPatient")
//    private Patient patient;
//
//    @ManyToOne
//    @JoinColumn(name = "idProfessional")
//    private Professional professional;
//
//    @Column(name = "appointment")
//    private LocalDateTime appointment;
//
//    //@Temporal(TemporalType.TIMESTAMP)
//    //private Date alta;
//
//    @Column(name = "duration")
//    private int duration = 30;
//
//    @Column(name = "state")
//    private String state;
//
//    @Column(name = "comments")
//    private String comments;
//
//
//
//}
