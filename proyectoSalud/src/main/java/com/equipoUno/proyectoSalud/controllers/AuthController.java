package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.dto.UserDTO;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.servicies.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/auth")
public class AuthController {
    private final UserServiceImplement userService;

    @Autowired
    public AuthController(UserServiceImplement userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("userDTO") UserDTO userDTO,
                           BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            System.out.println(bindingResult.toString());
            model.addAttribute("error", bindingResult.toString());
            return "register";
        }
//        try {
            userService.registerUser(userDTO);
            return "redirect:/login";
//        } catch (MiException e) {
//            model.addAttribute("error", e.getMessage());
//            model.addAttribute("userDTO", userDTO);
//            return "register";
//        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            model.addAttribute("error", "Usuario o Contraseña invalidos");
        }
        return "login";
    }

    // @PostMapping("/{userId}/patients")
    // public ResponseEntity<String> assignPatientUser(@PathVariable String userId,
    // @RequestBody PatientDTO patientDTO) {
    // userService.assignPatientUser(userId, patientDTO);
    // return ResponseEntity.ok("Paciente asignado correctamente");
    // }

    // @PostMapping("/{userId}/professionals")
    // public ResponseEntity<String> assignProfessionalUser(@PathVariable String
    // userId, @RequestBody ProfessionalDTO professionalDTO) {
    // userService.assignProfessionalUser(userId, professionalDTO);
    // return ResponseEntity.ok("Profesional asignado correctamente");
    // }

}
