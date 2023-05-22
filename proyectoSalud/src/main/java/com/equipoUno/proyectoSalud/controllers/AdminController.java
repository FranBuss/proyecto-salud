package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.entities.Patient;
import com.equipoUno.proyectoSalud.servicies.PatientServiceImplement;
import com.equipoUno.proyectoSalud.servicies.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImplement userServiceImplement;

    private final PatientServiceImplement patientServiceImplement;

    @Autowired
    public AdminController(UserServiceImplement userServiceImplement, PatientServiceImplement patientServiceImplement){
        this.userServiceImplement = userServiceImplement;
        this.patientServiceImplement = patientServiceImplement;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard.html";
    }

    @GetMapping ("/patients")
    public String listPatients(ModelMap model) {
        List<PatientDTO> patients = patientServiceImplement.findAllPatients();
        model.addAttribute("patients", patients);
        return "patients";
    }


}
