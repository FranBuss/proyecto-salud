package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.ProfessionalDTO;
import com.equipoUno.proyectoSalud.entities.Professional;

import java.util.List;

public interface ProfessionalService {
    List<Professional> searchProfessionals();

    void createProfessional(ProfessionalDTO dto);

    void updateProfessional(String id);

    void deleteProfessional(String id);

    void updateDropOut(String id);
}
