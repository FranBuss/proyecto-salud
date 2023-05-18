package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.ProfessionalDTO;
import com.equipoUno.proyectoSalud.entities.Professional;

import java.util.List;

public interface ProfessionalService {
    List<Professional> searchProfessionals();

    List<Professional> searchProfessionalsBySpecialization(String specializationString);

    ProfessionalDTO createProfessional(ProfessionalDTO dto);

    ProfessionalDTO updateProfessional(String id, ProfessionalDTO dto);

    void deleteProfessional(String id);

    ProfessionalDTO updateDropOut(String id);
}
