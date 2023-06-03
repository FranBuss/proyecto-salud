package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.dto.ProfessionalDTO;
import com.equipoUno.proyectoSalud.entities.Patient;
import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.enumerations.Rol;
import com.equipoUno.proyectoSalud.enumerations.Specialization;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.repositories.ProfessionalRepository;
import com.equipoUno.proyectoSalud.utils.ProfessionalUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProfessionalServiceImplement implements ProfessionalService{

    private final ProfessionalRepository professionalRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ProfessionalServiceImplement(ProfessionalRepository professionalRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.professionalRepository = professionalRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public ProfessionalDTO getProfessional(String id){
        Optional<Professional> optionalProfessional = professionalRepository.findById(id);
        if (optionalProfessional.isPresent()){
            Professional professional = optionalProfessional.get();
            return modelMapper.map(professional, ProfessionalDTO.class);
        }
        return null;
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
    public List<Professional> searchProfessionalsBySpecialization(String specializationString) {
        Specialization specialization = ProfessionalUtil.getSpecialization(specializationString);
        return this.professionalRepository.searchBySpecialization(specialization);
    }

    @Override
    public ProfessionalDTO createProfessional(ProfessionalDTO dto) {
        Professional professional = modelMapper.map(dto, Professional.class);
        professional = professionalRepository.save(professional);
        return modelMapper.map(professional, ProfessionalDTO.class);
    }

    @Override
    public Professional updateProfessional(String id){
        Optional<Professional> professionalInfo = professionalRepository.findById(id);
        if (professionalInfo.isPresent()) {
            Professional professional = professionalRepository.save(professionalInfo.get());
            return professional;
        }
        return null;
    }

    @Override
    public void deleteProfessional(String id) throws MiException {
        professionalRepository.deleteById(id);
    }

    @Override
    public ProfessionalDTO updateDropOut(String id) throws MiException {
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
