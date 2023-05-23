package com.equipoUno.proyectoSalud.repositories;

import com.equipoUno.proyectoSalud.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {

}
