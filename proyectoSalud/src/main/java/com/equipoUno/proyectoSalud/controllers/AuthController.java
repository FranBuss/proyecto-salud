package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.repositories.UserRepository;
import com.equipoUno.proyectoSalud.servicies.UserService;
import com.equipoUno.proyectoSalud.servicies.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping ("/registrar")
    public String registrar() {
        return "";
    }

    //falta completar
    @PostMapping("/registro")
    public String registro(MultipartFile file, @RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String password2, ModelMap modelo){
        try {
            userService.register(file, name, email, password, password2);
            modelo.put("exito", "Usuario registrado correctamente");
            return "index.html";
        } catch (MiException ex) {
            modelo.put("error", ex.getMessage());
            modelo.put("nombre", name);
            modelo.put("email", email);
            return ".html";  //registro.html?
        }


    }

    @PostMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo){

        if(error != null) {
            modelo.put("error", "Usuario o contraseña invalidos!");
        }
        return "login.html";
    }



}
