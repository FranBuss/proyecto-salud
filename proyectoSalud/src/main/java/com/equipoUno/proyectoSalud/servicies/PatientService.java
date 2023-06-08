package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.entities.Patient;
import com.equipoUno.proyectoSalud.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface PatientService {
    void createPatient(Patient patient);
    PatientDTO getPatient(String id);
    Patient getPatientByUserId(String id);
    Patient updatePatient(String id);

    List<PatientDTO> findAllPatients();

    void deletePatient(String id);

    List<Patient> getPatientsWithMedicalRecord();

}
