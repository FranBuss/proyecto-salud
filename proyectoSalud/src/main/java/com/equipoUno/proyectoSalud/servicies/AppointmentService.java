package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.AppointmentDTO;
import com.equipoUno.proyectoSalud.entities.Appointment;
import com.equipoUno.proyectoSalud.entities.Patient;
import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.exceptions.MiException;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentService {

    void generateAppointments(Professional professional);

    List<Appointment> getAppointmentsByProfessional(String id);

    void deleteAppointment(String id);

    void deleteAppointmentAvailable(String id);

    void assignAppointment(Patient patient, String AppId);

    Appointment getAppointmentById(String id);

//    boolean isAppointmentAvailable(AppointmentDTO dto);
//
//    List<AppointmentDTO> occupiedAppointmentsDTO();
//
//    AppointmentDTO updateAppointmentDate(String id, AppointmentDTO dto, LocalDateTime newTime) throws MiException;
//
//    List<AppointmentDTO> availableAppointments();
}
