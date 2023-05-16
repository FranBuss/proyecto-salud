package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.exceptions.MiException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
    void register(MultipartFile file, String name, String email, String password, String password2) throws MiException;
    void validate(String name, String email, String password, String password2) throws MiException;


}
