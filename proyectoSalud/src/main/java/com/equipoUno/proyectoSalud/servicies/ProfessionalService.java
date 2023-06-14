package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.dto.ProfessionalDTO;
import com.equipoUno.proyectoSalud.entities.Appointment;
import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.exceptions.MiException;

import java.util.List;

public interface ProfessionalService {

    ProfessionalDTO getProfessional(String id);

    List<Professional> searchProfessionals();

    Professional getProfessionalByUserId(String id);

    Professional getProfessionalById(String id);

    List<Appointment> getAssignAppointment(String professionalId);

    List<Professional> searchProfessionalsBySpecialization(String specializationString) throws MiException;

    ProfessionalDTO createProfessional(ProfessionalDTO dto) throws MiException;

    ProfessionalDTO updateProfessional(String id, ProfessionalDTO professionalDTO);

    void deleteProfessional(String id) throws MiException;

    ProfessionalDTO updateDropOut(String id) throws MiException;
}
