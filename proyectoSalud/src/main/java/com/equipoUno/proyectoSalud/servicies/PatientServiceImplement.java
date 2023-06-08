package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.entities.Patient;
import com.equipoUno.proyectoSalud.enumerations.Rol;
import com.equipoUno.proyectoSalud.repositories.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientServiceImplement implements PatientService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PatientServiceImplement(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void createPatient(Patient patient) {
        patientRepository.save(patient);
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
    public Patient getPatientByUserId(String id) {
        Optional<Patient> patientResponse = patientRepository.getPatientByUserId(id);
        if (patientResponse.isPresent()) {
            Patient patient = patientResponse.get();
            return patient;
        }
        return null;
    }

    @Override
    public Patient updatePatient(String id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if (optionalPatient.isPresent()) {
            Patient patient = patientRepository.save(optionalPatient.get());
            return patient;
        }
        return null;
    }

    @Override
    public void deletePatient(String id) {
        patientRepository.deleteById(id);
    }


    @Override
    public List<PatientDTO> findAllPatients(){
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patient -> modelMapper.map(patient, PatientDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<Patient> getPatientsWithMedicalRecord() {
        List<Object[]> objects = patientRepository.getPatientsWithMedicalRecord();
        List<Patient> patients = new ArrayList<>();
        for (Object[] object : objects) {
            patients.add((Patient) object[0]);
        }
        return patients;
    }
}
