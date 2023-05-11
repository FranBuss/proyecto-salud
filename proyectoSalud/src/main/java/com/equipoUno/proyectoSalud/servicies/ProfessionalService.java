package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.ProfessionalDTO;
import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.repositories.ProfessionalRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfessionalService {

    private final ProfessionalRepository professionalRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProfessionalService(ProfessionalRepository professionalRepository, ModelMapper modelMapper){
        this.professionalRepository = professionalRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProfessionalDTO> getAllProfessionals() {
        List<Professional> professionals = professionalRepository.findAll();
        return professionals.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ProfessionalDTO getProfessionalById(String id) {
        Optional<Professional> professional = professionalRepository.findById(id);
        return professional.map(this::convertToDTO).orElse(null);
    }

    public ProfessionalDTO createProfessional(ProfessionalDTO professionalDTO) {
        Professional professional = convertToEntity(professionalDTO);
        Professional createdProfessional = professionalRepository.save(professional);
        return convertToDTO(createdProfessional);
    }

    public ProfessionalDTO updateProfessional(String id, ProfessionalDTO professionalDTO) {
        Optional<Professional> existingProfessional = professionalRepository.findById(id);
        if (existingProfessional.isPresent()) {
            Professional professional = existingProfessional.get();
            modelMapper.map(professionalDTO, professional);
            Professional updatedProfessional = professionalRepository.save(professional);
            return convertToDTO(updatedProfessional);
        }
        return null;
    }

    public boolean deleteProfessional(String id) {
        Optional<Professional> professional = professionalRepository.findById(id);
        if (professional.isPresent()) {
            professionalRepository.delete(professional.get());
            return true;
        }
        return false;
    }

    private ProfessionalDTO convertToDTO(Professional professional) {
        return modelMapper.map(professional, ProfessionalDTO.class);
    }

    private Professional convertToEntity(ProfessionalDTO professionalDTO) {
        return modelMapper.map(professionalDTO, Professional.class);
    }
}
