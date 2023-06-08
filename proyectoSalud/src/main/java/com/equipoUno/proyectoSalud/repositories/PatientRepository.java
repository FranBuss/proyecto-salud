package com.equipoUno.proyectoSalud.repositories;

import com.equipoUno.proyectoSalud.entities.Patient;
import com.equipoUno.proyectoSalud.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, String> {
    // @Query("SELECT u FROM Patient u WHERE u.email = :email")
    // public Patient findByEmail(String email);

    public Optional<Patient> findPatientByUser(User user);

    @Query("SELECT p FROM Patient p WHERE p.user.id = :id")
    public Optional<Patient> getPatientByUserId(@Param("id") String id);

    @Query("SELECT p, u.name, u.surname FROM Patient p JOIN MedicalRecord mr ON p.id = mr.patient.id JOIN User u ON u.id = p.user.id GROUP BY p.id")
    public List<Object[]> getPatientsWithMedicalRecord();
}
