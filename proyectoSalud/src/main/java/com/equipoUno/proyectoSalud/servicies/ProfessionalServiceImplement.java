package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.ProfessionalDTO;
import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.repositories.ProfessionalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;


@Service
public class ProfessionalServiceImplement implements ProfessionalService {

    private final ProfessionalRepository professionalRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProfessionalServiceImplement(ProfessionalRepository professionalRepository, ModelMapper modelMapper) {
        this.professionalRepository = professionalRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Professional> searchProfessionals() {
        try {
            List<Professional> professionals = professionalRepository.findAll();
            if (professionals == null) {
                throw new MiException("No se han encontrado profesionales");
            }
            return professionals;
        } catch (MiException e) {
            return null;
        }
    }

    @Override
    public ProfessionalDTO createProfessional(ProfessionalDTO dto) {
        Professional professional = modelMapper.map(dto, Professional.class);
        professional = professionalRepository.save(professional);
        return modelMapper.map(professional, ProfessionalDTO.class);
    }

    @Override
    public ProfessionalDTO updateProfessional(ProfessionalDTO dto) {
        Optional<Professional> professionalInfo = professionalRepository.findById(dto.getId());
        if (professionalInfo.isPresent()) {
            Professional professional = professionalInfo.get();
            professional.setEntryTime(dto.getEntryTime());
            professional.setExitTime(dto.getExitTime());
            professional = professionalRepository.save(professional);
            return modelMapper.map(professional, ProfessionalDTO.class);
        }
        return null;
    }

    @Override
    public void deleteProfessional(String id) {
        professionalRepository.deleteById(id);
    }

    @Override
    public ProfessionalDTO updateDropOut(String id) {
        Optional<Professional> professionalInfo = professionalRepository.findById(id);
        if (professionalInfo.isPresent()) {
            Professional professional = professionalInfo.get();
            if (professional.isDropOut()) {
                professional.setDropOut(false);
            } else {
                professional.setDropOut(true);
            }
            professional = professionalRepository.save(professional);
            return modelMapper.map(professional, ProfessionalDTO.class);
        }
        return null;
    }
}
