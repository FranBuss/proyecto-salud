package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.dto.ProfessionalDTO;
import com.equipoUno.proyectoSalud.entities.Professional;

import java.util.List;

public interface ProfessionalService {

    ProfessionalDTO getProfessional(String id);
    List<Professional> searchProfessionals();

    List<Professional> searchProfessionalsBySpecialization(String specializationString);

    ProfessionalDTO createProfessional(ProfessionalDTO dto);

    Professional updateProfessional(String id);

    void deleteProfessional(String id);

    ProfessionalDTO updateDropOut(String id);
}
