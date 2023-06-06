package com.equipoUno.proyectoSalud.repositories;

import com.equipoUno.proyectoSalud.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    List<Appointment> findByProfessionalId(String professionalId);

    @Query("SELECT a FROM Appointment a ORDER BY a.date ASC, a.appointment ASC")
    public List<Appointment> getAllAppointmentsByOrder();

    @Query("DELETE FROM Appointment a WHERE a.state = 1 AND a.id_professional = :id")
    public List<Appointment> deleteAllAppointmentsAvailable(@Param("id") String id);
}
