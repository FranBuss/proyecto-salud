package com.equipoUno.proyectoSalud.controllers;


import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.entities.Patient;
import com.equipoUno.proyectoSalud.servicies.PatientService;
import com.equipoUno.proyectoSalud.servicies.PatientServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/api/auth")
public class AuthController {


    private final PatientServiceImplement patientService;

    @Autowired
    public AuthController(PatientServiceImplement patientService){
        this.patientService = patientService;
    }

    @PostMapping ("/register")
    public String register(@Validated @ModelAttribute("patientDTO") PatientDTO patientDTO, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            model.addAttribute("patientDTO",patientDTO);
            return "register";
        }

        try {
            patientService.createPatient(patientDTO);
            return "redirect:/login";
        }catch(RuntimeException e){
            model.addAttribute("error","Se produjo un error durante el registro.");
            model.addAttribute("patientDTO",patientDTO);
            return "register";
        }

    }
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, Model model){

        if (error != null) {
            model.addAttribute("error", "Usuario o Contraseña invalidos");
        }

        return "login";

    }


    @GetMapping("/patient/{email}")
    public String getUserDetails(@PathVariable String email, Model model) {
        UserDetails userDetails = patientService.loadUserByUsername(email);

        if (userDetails == null) {
            return "error-page";
        }

        model.addAttribute("userDetails", userDetails);

        return "user-details";
    }

}
