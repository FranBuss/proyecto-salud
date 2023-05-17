package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.repositories.UserRepository;
import com.equipoUno.proyectoSalud.servicies.UserService;
import com.equipoUno.proyectoSalud.servicies.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;

    @GetMapping ("/register")
    public String register(@RequestBody String name,String email, String password, String confirmpassword) {
        return "redirect:/register";
    }

    //falta completar
//    @PostMapping("/registro")
//    public String register(MultipartFile file, @RequestParam String name, @RequestParam String email, @RequestParam String password, @RequestParam String password2, ModelMap modelo){
//        try {
//            userService.register(file, name, email, password, password2);
//            modelo.put("exito", "Usuario registrado correctamente");
//            return "index.html";
//        } catch (MiException ex) {
//            modelo.put("error", ex.getMessage());
//            modelo.put("nombre", name);
//            modelo.put("email", email);
//            return ".html";  //registro.html?
//        }
//
//
//    }

    @PostMapping("/login")
    public String login(@RequestBody String email, String password){
        return "redirect:/login";
    }



}
