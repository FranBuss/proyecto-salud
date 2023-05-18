package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.PatientDTO;

public interface PatientService {
    PatientDTO createPatient(PatientDTO dto);

    PatientDTO getPatient(String id);

    PatientDTO updatePatient(String id, PatientDTO dto);

    void deletePatient(String id);

}
