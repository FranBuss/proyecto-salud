package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.ProfessionalDTO;
import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.repositories.ProfessionalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProfessionalServiceImplement implements ProfessionalService{

    private ProfessionalRepository professionalRepository;

    public  ProfessionalServiceImplement(ProfessionalRepository professionalRepository){
        this.professionalRepository = professionalRepository;
    }

    @Override
    public List<Professional> searchProfessionals() {
        return null;
    }

    @Override
    public void createProfessional(ProfessionalDTO dto) {

    }

    @Override
    public void updateProfessional(String id) {

    }

    @Override
    public void deleteProfessional(String id) {

    }
    @Override
    public void updateDropOut(String id) {

    }
}
