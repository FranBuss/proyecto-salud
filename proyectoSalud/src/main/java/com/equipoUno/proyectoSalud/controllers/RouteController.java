package com.equipoUno.proyectoSalud.controllers;


import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.entities.Patient;
import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.enumerations.Specialization;
import com.equipoUno.proyectoSalud.servicies.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class RouteController {

    @Autowired
    private  ProfessionalService professionalService;

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, Model model){

        if (error != null) {
            model.addAttribute("error", "Usuario o Contraseña invalidos");
        }

        return "login";

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/admin/professionals")
    public String professionals(Model model){
        List<Professional> professionals = professionalService.searchProfessionals();
        model.addAttribute("professionals",professionals);
        return "professionals";
    }

    @PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_ADMIN', 'ROLE_PROFESSIONAL')")
    @GetMapping("/index")
    public String index(HttpSession session){

        Patient loggedPatient = (Patient) session.getAttribute("patientSession");


        if (loggedPatient.getRol().toString().equals("ADMIN")){
            return "redirect:api/admin/dashboard";
        }

//        if (loggedPatient.getRoles().equals("PROFESSIONAL")){
//            return "redirect:api/professional/index";
//        }

        return "index";

    }

    @GetMapping("/register")
    public String register(Model model){
        PatientDTO patientDTO = new PatientDTO();
        model.addAttribute("patientDTO", patientDTO);
        return "register";
    }

    @GetMapping("/searcher")
    public String searcher(Model model,  @RequestParam Map<String, String> queryParams){
        Specialization[] specializations = Specialization.values();
        model.addAttribute("specializations",specializations);
        if(!queryParams.isEmpty()){
            String specialization = queryParams.get("specialization").toString();
            List<Professional> professionals = professionalService.searchProfessionalsBySpecialization(specialization);
            model.addAttribute("professionals",professionals);
        }
        return "searcher";
    }

}
