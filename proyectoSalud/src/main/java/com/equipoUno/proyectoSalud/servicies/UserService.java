package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.UserDTO;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

public interface UserService{


//    void register(MultipartFile file, String name, String email, String password, String password2) throws MiException;

    UserDTO registerUser(UserDTO userDTO);

    UserDTO updateUser(String id, UserDTO userDTO) throws MiException;

//    void validate(String name, String email, String password, String password2) throws MiException;

//    void updateUser(String id ,String name, String email, String password, String password2) throws MiException;

}
