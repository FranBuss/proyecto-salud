package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.dto.UserDTO;
import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.entities.User;
import com.equipoUno.proyectoSalud.enumerations.EmailDomain;
import com.equipoUno.proyectoSalud.enumerations.Specialization;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.servicies.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class RouteController {

    private final UserServiceImplement userService;
    private final PatientServiceImplement patientService;
    private final ProfessionalServiceImplement professionalService;

    @Autowired
    public RouteController(UserServiceImplement userService, PatientServiceImplement patientService, ProfessionalServiceImplement professionalService){
        this.userService = userService;
        this.patientService = patientService;
        this.professionalService = professionalService;
    }


    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, Model model) {

        if (error != null) {
            model.addAttribute("error", "Usuario o Contraseña invalidos");
        }

        return "login";

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/admin/professionals")
    public String professionals(Model model) {
        List<Professional> professionals = professionalService.searchProfessionals();
        model.addAttribute("professionals", professionals);
        return "professionals";
    }

    @PreAuthorize("hasAnyRole('ROLE_PROFESSIONAL')")
    @GetMapping("/professionals/patients")
    public String patients(Model model){
        List<PatientDTO> patients = patientService.findAllPatients();
        model.addAttribute("patients",patients);
        return "patients";
    }

    @PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_ADMIN', 'ROLE_PROFESSIONAL')")
    @GetMapping("/index")
    public String index(HttpSession session) {

        User loggedUser = (User) session.getAttribute("userSession");

        if (loggedUser.getRol().toString().equals("ADMIN")) {
            return "redirect:api/admin/dashboard";
        }

        if (loggedUser.getRol().toString().equals("PROFESSIONAL")) {
            return "redirect:api/professional/index";
        }

        return "index";

    }

    @GetMapping("/register")
    public String register(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("emailDomain", EmailDomain.values());
        return "register";
    }

    @GetMapping("/searcher")
    public String searcher(Model model, @RequestParam Map<String, String> queryParams) throws MiException {
        Specialization[] specializations = Specialization.values();
        model.addAttribute("specializations", specializations);
        if (!queryParams.isEmpty()) {
            String specialization = queryParams.get("specialization").toString();
            List<Professional> professionals = professionalService.searchProfessionalsBySpecialization(specialization);
            model.addAttribute("professionals", professionals);
        }
        return "searcher";
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

    @GetMapping("/patient/appointments")
    public String adminAppointments() {
        return "appointments";
    }

    @GetMapping("/patient/getAppointment")
    public String getAppointments() {
        return "getAppointment";
    }
}
