package com.equipoUno.proyectoSalud.repositories;

import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.enumerations.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, String> {
    @Query("SELECT p FROM Professional p WHERE p.specialization = :specialization ORDER BY p.charge, p.qualification DESC")
    List<Professional> searchBySpecialization(Specialization specialization);


}
