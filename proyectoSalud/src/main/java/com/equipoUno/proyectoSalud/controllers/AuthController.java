package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.dto.UserDTO;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.repositories.UserRepository;
import com.equipoUno.proyectoSalud.servicies.UserService;
import com.equipoUno.proyectoSalud.servicies.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final UserRepository userRepository;

    private final UserServiceImplement userServiceImplement;

    @Autowired
    public AuthController(UserRepository userRepository, UserServiceImplement userServiceImplement){
        this.userRepository = userRepository;
        this.userServiceImplement = userServiceImplement;
    }


    @GetMapping ("/register")
    public String register(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO userDTO) {
        UserDTO registeredUserDTO = userServiceImplement.registerUser(userDTO);
        return ResponseEntity.ok(registeredUserDTO);
    }

    @PostMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo){

        if(error != null) {
            modelo.put("error", "Usuario o contraseña invalidos!");
        }
        return "login.html";
    }



}
