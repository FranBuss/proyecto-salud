package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.dto.ProfessionalDTO;
import com.equipoUno.proyectoSalud.dto.UserDTO;
import com.equipoUno.proyectoSalud.entities.Patient;
import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.entities.User;
import com.equipoUno.proyectoSalud.enumerations.Rol;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.repositories.PatientRepository;
import com.equipoUno.proyectoSalud.repositories.ProfessionalRepository;
import com.equipoUno.proyectoSalud.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements UserService, UserDetailsService {

    private  final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final ProfessionalRepository professionalRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImplement(ProfessionalRepository professionalRepository, PatientRepository patientRepository, UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.professionalRepository = professionalRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO){
        User user = modelMapper.map(userDTO, User.class);
        user.setRol(Rol.PATIENT);
        user.setEmail(userDTO.getEmail().concat(userDTO.getEmailSuffix()));
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);
    }

    @Override
    public UserDTO updateUser(String id, UserDTO userDTO) throws MiException{
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()){
            User user = optionalUser.get();
            modelMapper.map(userDTO, user);
            User updateUser = userRepository.save(user);
            return modelMapper.map(updateUser, UserDTO.class);
        } else {
            throw new MiException("User Not Found");
        }
    }

    @Override
    public PatientDTO assignPatientUser(String userId, PatientDTO patientDTO) {
        User user = userRepository.findById(userId).orElse(null);
        Patient patient = modelMapper.map(patientDTO, Patient.class);
        patient.setUser(user);
        Patient savePatient = patientRepository.save(patient);
        return modelMapper.map(savePatient, PatientDTO.class);
    }

    @Override
    public ProfessionalDTO assignProfessionalUser(String userId, ProfessionalDTO professionalDTO) {
        User user = userRepository.findById(userId).orElse(null);
        if (user.getRol().toString().equals("PATIENT")){
            user.setRol(Rol.PROFESSIONAL);
            userRepository.save(user);
        }
        Professional professional = modelMapper.map(professionalDTO, Professional.class);
        professional.setUser(user);
        Professional saveProfessional = professionalRepository.save(professional);
        return modelMapper.map(saveProfessional, ProfessionalDTO.class);

//        if (user != null) {
//            Professional professional = new Professional();
//
//            professional.setSpecialization(professionalDTO.getSpecialization());
//            professional.setEntryTime(professionalDTO.getEntryTime());
//            professional.setExitTime(professionalDTO.getExitTime());
//            professional.setCharge(professional.getCharge());
//            professional.setQualification(professional.getQualification());
//
//            professional.setUser(user);
//            professionalRepository.save(professional);
//        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user != null){
            List<GrantedAuthority> auths = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + user.getRol().toString());

            auths.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("userSession", user);

            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), auths);

        } else {
            return null;
        }

    }


}
