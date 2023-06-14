package com.equipoUno.proyectoSalud.repositories;

import com.equipoUno.proyectoSalud.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, String> {

    List<Appointment> findByProfessionalId(String professionalId);

    @Modifying
    @Query("DELETE FROM Appointment a WHERE a.state = true AND a.professional.id = :id")
    void deleteAllAppointmentsAvailable(@Param("id") String id);

    @Query("SELECT a FROM Appointment a WHERE a.professional.id = :professionalId ORDER BY a.date ASC, a.appointment ASC")
    List<Appointment> getAppointmentsByProfessionalId(@Param("professionalId") String id);

    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientId ORDER BY a.date ASC, a.appointment ASC")
    List<Appointment> getAppointmentsByPatient(@Param("patientId") String id);

    @Query("SELECT a FROM Appointment a WHERE a.professional.id = :professionalId AND a.state = FALSE" +
            " ORDER BY a.date ASC, a.appointment ASC")
    List<Appointment> getAssignAppointment(@Param("professionalId") String id);

    @Modifying
    @Query("UPDATE Appointment a SET a.state = true, a.patient.id = null, a.comments = null WHERE a.id = :appointmentId")
    void enableAppointment(@Param("appointmentId") String id);

    @Query("SELECT a.professional.id FROM Appointment a WHERE a.id = :appointmentId")
    String getProfessionalByIdAppointment(@Param("appointmentId") String id);

    @Modifying
    @Query("DELETE FROM Appointment a WHERE a.id = :appId")
    void deleteAppointmentById(@Param("appId") String id);
}
