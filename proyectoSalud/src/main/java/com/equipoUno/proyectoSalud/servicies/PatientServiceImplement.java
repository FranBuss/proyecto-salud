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
public class PatientServiceImplement implements PatientService, UserDetailsService {

    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;
    @Autowired
    public PatientServiceImplement(PatientRepository patientRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PatientDTO createPatient(PatientDTO dto) {
        Patient patient = modelMapper.map(dto, Patient.class);
        patient.setRol(Rol.PATIENT);
        patient.setEmail(dto.getEmail().concat(dto.getEmailSuffix()));
        patient.setPassword(passwordEncoder.encode(dto.getPassword()));
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Patient patient = patientRepository.findByEmail(email);

        if (patient != null){
            List<GrantedAuthority> auths = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + patient.getRol().toString());

            auths.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("patientSession", patient);

            return new User(patient.getEmail(), patient.getPassword(), auths);
        } else {
            return null;
        }

    }

    @Override
    public List<PatientDTO> findAllPatients(){
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(patient -> modelMapper.map(patient, PatientDTO.class))
                .collect(Collectors.toList());
    }

//    private PatientDTO converToDTO(Patient patient){
//        return modelMapper.map(patient, PatientDTO.class);
//    }

//    private void setPatientSessionAttribute(PatientDTO patientDTO){
//        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
//        HttpSession session = attr.getRequest().getSession(true);
//        session.setAttribute("patientSession", patientDTO);
//    }

//    private UserDetails buildUserDetails(PatientDTO patientDTO){
//        List<SimpleGrantedAuthority> authorities = patientDTO.getRol()
//                .stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//
//        return User.withUsername(patientDTO.getEmail()) //si no funciona intentar .getUsername()
//                .password(patientDTO.getPassword())
//                .authorities(authorities)
//                .build();
//
//    }




}
