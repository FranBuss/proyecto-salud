package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.entities.Appointment;
import com.equipoUno.proyectoSalud.entities.Patient;
import com.equipoUno.proyectoSalud.entities.Professional;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {

    void generateAppointments(Professional professional);

    List<Appointment> getAppointmentsByProfessionalId(String id);

    List<Appointment> getAppointmentsByPatient(String id);

    void deleteAppointmentAvailable(String id);

    void assignAppointment(Patient patient, String AppId, String comment);

    Optional<Appointment> getAppointmentById(String id);

    String getProfessionalByIdAppointment(String id);

    void resetAppointmentById(String id);

    void deleteUsedAppointmentById(String id);
}
