package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.entities.Patient;
import com.equipoUno.proyectoSalud.repositories.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImplement implements PatientService{

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PatientServiceImplement(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PatientDTO createPatient(PatientDTO dto) {
        Patient patient = modelMapper.map(dto, Patient.class);
        patient = patientRepository.save(patient);
        return modelMapper.map(patient, PatientDTO.class);
    }

    @Override
    public PatientDTO getPatient(String id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            return modelMapper.map(patient, PatientDTO.class);
        }
        return null;
    }

    @Override
    public PatientDTO updatePatient(String id, PatientDTO dto) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient patient = optionalPatient.get();
            patient.setHealthInsurance(dto.getHealthInsurance());
            patient.setContact(dto.getContact());
            patient = patientRepository.save(patient);
            return modelMapper.map(patient, PatientDTO.class);
        }
        return null;
    }

    @Override
    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        PatientDTO patientDTO = patientRepository.findByEmail(email)
                .map(this::converToDTO)
                .orElseThrow(()-> new UsernameNotFoundException("Patient not found"));

        return buildUserDetails(patientDTO);
    }

    private PatientDTO converToDTO(Patient patient){
        return modelMapper.map(patient, PatientDTO.class);
    }

    private UserDetails buildUserDetails(PatientDTO patientDTO){
        List<SimpleGrantedAuthority> authorities = patientDTO.getRoles()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return User.withUsername(patientDTO.getEmail()) //si no funciona intentar .getUsername()
                .password(patientDTO.getPassword())
                .authorities(authorities)
                .build();

    }
}
