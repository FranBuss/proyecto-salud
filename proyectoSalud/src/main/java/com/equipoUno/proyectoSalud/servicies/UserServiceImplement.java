package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.entities.Image;
import com.equipoUno.proyectoSalud.entities.User;
import com.equipoUno.proyectoSalud.enumerations.Rol;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplement implements UserService, UserDetailsService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ImageService imageService;
    @Transactional
    public void register(MultipartFile file, String name, String email, String password, String password2) throws MiException{

        validate(name, email, password, password2);

        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        user.setRol(Rol.PATIENT);

        Image image = imageService.save(file);
        user.setImage(image);

        userRepository.save(user);
    }

    public void validate(String name, String email, String password, String password2) throws MiException {
        if (name.isEmpty() || name == null ) {
            throw new MiException("El nombre no puede ser nulo o estar vacio");
        }
        if (email.isEmpty() || email == null ) {
            throw new MiException("El email no puede ser nulo o estar vacio");
        }
        if (password.isEmpty() || password == null || password.length() <= 5) {
            throw new MiException("La contraseña no puede estar vacia, y debe tener más de 5 digitos");
        }

        if (!password.equals(password2)) {
            throw new MiException("Las contraseñas ingresadas deben ser iguales");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user != null) {

            List<GrantedAuthority> permits = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + user.getRol().toString());

            permits.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("usersession", user);

            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), permits);
        } else {
            return null;
        }
    }





}
