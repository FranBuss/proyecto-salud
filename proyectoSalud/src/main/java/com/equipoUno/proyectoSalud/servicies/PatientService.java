package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface PatientService {
    PatientDTO createPatient(PatientDTO dto);
    PatientDTO getPatient(String id);

    PatientDTO updatePatient(String id, PatientDTO dto);

    List<PatientDTO> findAllPatients();

    void deletePatient(String id);

}
