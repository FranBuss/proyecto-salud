package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    void register(String name, String email, String password, String password2);

    void validate(String name, String email, String password, String password2);

    //UserDetails loadUserByUsername(String email);

}
