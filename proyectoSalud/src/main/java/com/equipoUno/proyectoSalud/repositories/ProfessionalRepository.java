package com.equipoUno.proyectoSalud.repositories;

import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.entities.User;
import com.equipoUno.proyectoSalud.enumerations.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, String> {
    @Query("SELECT p, u.name, u.surname FROM Professional p JOIN p.user u WHERE p.specialization = :specialization ORDER BY p.charge, p.qualification DESC")
    List<Professional> searchBySpecialization(@Param("specialization") Specialization specialization);

    Professional findByUser_Id(String id);
}
