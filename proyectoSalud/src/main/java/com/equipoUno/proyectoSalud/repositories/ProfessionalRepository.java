package com.equipoUno.proyectoSalud.repositories;

import com.equipoUno.proyectoSalud.entities.Professional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessionalRepository extends JpaRepository<Professional, String> {
}
