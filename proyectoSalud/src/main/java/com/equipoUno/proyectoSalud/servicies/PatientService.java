package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.entities.Patient;

public interface PatientService {
    void createPatient(PatientDTO dto);

    Patient getPatient(String id);

    void updatePatient(String id);

    void deletePatient(String id);

}
