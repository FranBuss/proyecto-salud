package com.equipoUno.proyectoSalud.controllers;


import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.servicies.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    PatientService patientService;

    @PostMapping ("/register")
    public String register(@Validated @RequestBody PatientDTO patientDTO, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            model.addAttribute("patientDTO",patientDTO);
            return "register";
        }

        try {
            patientService.createPatient(patientDTO);
            return "redirect:/register";
        }catch(RuntimeException e){
            model.addAttribute("error","Se produjo un error durante el registro.");
            model.addAttribute("patientDTO",patientDTO);
            return "register";
        }

    }

    @PostMapping("/login")
    public String login(@RequestBody String email, String password){
        return "redirect:/login";
    }


}
