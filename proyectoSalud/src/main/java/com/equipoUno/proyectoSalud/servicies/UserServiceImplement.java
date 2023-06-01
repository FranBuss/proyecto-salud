package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.dto.ProfessionalDTO;
import com.equipoUno.proyectoSalud.dto.UserDTO;
import com.equipoUno.proyectoSalud.entities.Image;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImplement implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final ProfessionalRepository professionalRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final ImageServiceImplement imageServiceImplement;

    @Autowired
    public UserServiceImplement(ProfessionalRepository professionalRepository, PatientRepository patientRepository, UserRepository userRepository, ModelMapper modelMapper, PasswordEncoder passwordEncoder, ImageServiceImplement imageServiceImplement) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.professionalRepository = professionalRepository;
        this.patientRepository = patientRepository;
        this.imageServiceImplement = imageServiceImplement;
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        User user = modelMapper.map(userDTO, User.class);
        user.setRol(Rol.PATIENT);
        user.setEmail(userDTO.getEmail().concat(userDTO.getEmailSuffix().getValue()));
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        MultipartFile imageFile = userDTO.getImageFile();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                Image image = imageServiceImplement.save(imageFile);
                user.setImage(image);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDTO.class);

    }

    @Override
    public User updateUser(String id, UserDTO userDTO, User userSession) throws MiException {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (userDTO.getEmail() != null && !userDTO.getEmail().isEmpty()) {
                user.setEmail(userDTO.getEmail().concat(userDTO.getEmailSuffix().getValue()));
                userSession.setEmail(user.getEmail());
            }
            if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
                System.out.println(userDTO.getPassword());
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                userSession.setPassword(user.getPassword());
            }
            if (userDTO.getImageFile() != null && !userDTO.getImageFile().isEmpty()) {
                MultipartFile imageFile = userDTO.getImageFile();
                Image image = user.getImage();
                String imageId = "";
                if (image != null) {
                    imageId = image.getId();
                }
                try {
                    Image updatedImage = imageServiceImplement.update(imageFile, imageId);
                    user.setImage(updatedImage);
                    userSession.setImage(user.getImage());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            userRepository.save(user);
            return userSession;
//            return modelMapper.map(user, UserDTO.class);
        } else {
            throw new MiException("User Not Found");
        }
    }

    @Override
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public PatientDTO assignPatientUser(String userId, PatientDTO patientDTO) {
        User user = userRepository.findById(userId).orElse(null);
        Optional<Patient> patientResponse = patientRepository.findPatientByUser(user);
        if (patientResponse.isPresent()) {
            Patient patient = patientResponse.get();
            if (patientDTO.getContact() != null && !patientDTO.getContact().isEmpty()) {
                patient.setContact(patientDTO.getContact());
            }
            if (patientDTO.getHealthInsurance() != null && !patientDTO.getHealthInsurance().toString().isEmpty()) {
                patient.setHealthInsurance(patientDTO.getHealthInsurance());
            }
            patientRepository.save(patient);
        } else {
            Patient patient = modelMapper.map(patientDTO, Patient.class);
            patient.setUser(user);
            patientRepository.save(patient);
        }
//        Patient savePatient = patientRepository.save(patient);
//        return modelMapper.map(savePatient, PatientDTO.class);
        return null;
    }

    @Override
    public ProfessionalDTO assignProfessionalUser(String userId, ProfessionalDTO professionalDTO) {
        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        if (user.getRol().toString().equals("PATIENT")) {
            user.setRol(Rol.PROFESSIONAL);
            userRepository.save(user);
            Professional professional = modelMapper.map(professionalDTO, Professional.class);
            professional.setUser(user);
            Professional saveProfessional = professionalRepository.save(professional);
            return modelMapper.map(saveProfessional, ProfessionalDTO.class);
        }
        return null;
    }


    @Override
    public List<User> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> modelMapper.map(user, User.class))
                .collect(Collectors.toList());
    }

    @Override
    public User getOne(String id) {
        return userRepository.getOne(id);
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user != null) {
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
