package com.equipoUno.proyectoSalud.repositories;

import com.equipoUno.proyectoSalud.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient,String> {
//    @Query("SELECT u FROM Patient u WHERE u.email = :email")
//    public Patient findByEmail(String email);
}
