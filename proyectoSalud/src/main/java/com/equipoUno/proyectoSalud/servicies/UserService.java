package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.dto.ProfessionalDTO;
import com.equipoUno.proyectoSalud.dto.UserDTO;
import com.equipoUno.proyectoSalud.entities.User;
import com.equipoUno.proyectoSalud.exceptions.MiException;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface UserService{


//    void register(MultipartFile file, String name, String email, String password, String password2) throws MiException;

    UserDTO registerUser(UserDTO userDTO);

    User updateUser(String id, UserDTO userDTO, User userSession) throws MiException;

    PatientDTO assignPatientUser(String userId, PatientDTO patientDTO);

    ProfessionalDTO assignProfessionalUser(String userId, ProfessionalDTO professionalDTO);

    List<User> findAllUsers();

    void deleteUser(String id);

    User getOne(String id);

//    void validate(String name, String email, String password, String password2) throws MiException;

//    void updateUser(String id ,String name, String email, String password, String password2) throws MiException;

}
