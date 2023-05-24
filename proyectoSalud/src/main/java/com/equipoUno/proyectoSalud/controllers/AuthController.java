package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.dto.ProfessionalDTO;
import com.equipoUno.proyectoSalud.dto.UserDTO;
import com.equipoUno.proyectoSalud.servicies.PatientServiceImplement;
import com.equipoUno.proyectoSalud.servicies.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final PatientServiceImplement patientService;
    private final UserServiceImplement userService;

    @Autowired
    public AuthController(PatientServiceImplement patientService, UserServiceImplement userService) {
        this.patientService = patientService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute("userDTO") UserDTO userDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("userDTO", userDTO);
            return "register";
        }

        try {
            userService.registerUser(userDTO);
            return "redirect:/login";
        } catch (RuntimeException e) {
            model.addAttribute("error", "Se produjo un error durante el registro.");
            model.addAttribute("userDTO", userDTO);
            return "register";
        }

    }

    @PostMapping("/{userId}/patients")
    public ResponseEntity<String> assignPatientUser(@PathVariable String userId, @RequestBody PatientDTO patientDTO) {
        userService.assignPatientUser(userId, patientDTO);
        return ResponseEntity.ok("Paciente asignado correctamente");
    }

    @PostMapping("/{userId}/professionals")
    public ResponseEntity<String> assignProfessionalUser(@PathVariable String userId, @RequestBody ProfessionalDTO professionalDTO) {
        userService.assignProfessionalUser(userId, professionalDTO);
        return ResponseEntity.ok("Profesional asignado correctamente");
    }

    @GetMapping("/user/{email}")
    public String getUserDetails(@PathVariable String email, Model model) {
        UserDetails userDetails = userService.loadUserByUsername(email);

        if (userDetails == null) {
            return "error-page";
        }

        model.addAttribute("userDetails", userDetails);

        return "user-details";
    }

}
