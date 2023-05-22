package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface PatientService extends UserDetailsService {
    PatientDTO createPatient(PatientDTO dto);
    PatientDTO getPatient(String id);

    PatientDTO updatePatient(String id, PatientDTO dto);

    void deletePatient(String id);

}
