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

    @Query("DELETE FROM Appointment a WHERE a.state = true AND a.professional.id = :id")
    public void deleteAllAppointmentsAvailable(@Param("id") String id);
  
//    @Query("SELECT a FROM Appointment a JOIN a.professional AS p " +
//            "WHERE a.professional.id = :professionalId AND p.id = :professionalId" +
//            " ORDER BY a.date ASC, a.appointment ASC")
    @Query("SELECT a FROM Appointment a WHERE a.professional.id = :professionalId ORDER BY a.date ASC, a.appointment ASC")
    public List<Appointment> getAppointmentsByProfessional(@Param("professionalId") String id);

    @Query("SELECT a FROM Appointment a WHERE a.professional.id = :professionalId AND a.state = FALSE" +
            " ORDER BY a.date ASC, a.appointment ASC")
    public List<Appointment> getAssingAppointment(@Param("professionalId") String id);

}
