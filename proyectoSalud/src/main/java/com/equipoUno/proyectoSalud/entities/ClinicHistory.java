//package com.equipoUno.proyectoSalud.entities;
//
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.GenericGenerator;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Data
//@NoArgsConstructor
//@Entity
//@Table(name = "clinic_histories")
//public class ClinicHistory {
//
//    @Id
//    @GeneratedValue(generator = "uuid")
//    @GenericGenerator(name = "uuid", strategy = "uuid2")
//    @Column(name = "id")
//    private String id;
//
//    @OneToOne(mappedBy = "clinic_history", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//    private Patient patient;
//
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<MedicalRecord> medicalRecords;
//
//}