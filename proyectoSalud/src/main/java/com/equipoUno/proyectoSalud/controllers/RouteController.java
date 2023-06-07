package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.dto.ProfessionalDTO;
import com.equipoUno.proyectoSalud.dto.UserDTO;
import com.equipoUno.proyectoSalud.entities.Image;
import com.equipoUno.proyectoSalud.entities.Patient;
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
import org.springframework.ui.ModelMap;
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
    public String index(HttpSession session, ModelMap model) {
        model = userService.getUserData(session, model);
        return "index.html";
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            System.out.println();
            model.addAttribute("error", "Usuario o Contraseña invalidos");
        }
        try {
            return "login";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/admin/professionals")
    public String professionals(Model model) {
        List<Professional> professionals = professionalService.searchProfessionals();
        model.addAttribute("professionals", professionals);
        return "professionals";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/admin/users")
    public String users(HttpSession session, ModelMap model){
        model = userService.getUserData(session, model);
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @PreAuthorize("hasAnyRole('ROLE_PROFESSIONAL')")
    @GetMapping("/professionals/patients")
    public String patients(Model model){
        List<PatientDTO> patients = patientService.findAllPatients();
        model.addAttribute("patients",patients);
        return "patients";
    }

    @GetMapping("/register")
    public String register(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("emailDomain", EmailDomain.values());
        return "register";
    }

    @GetMapping("/searcher")
    public String searcher(HttpSession session, ModelMap model, @RequestParam Map<String, String> queryParams) throws MiException {
        model = userService.getUserData(session, model);
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

    @PreAuthorize("hasAnyRole('ROLE_PATIENT', 'ROLE_ADMIN', 'ROLE_PROFESSIONAL')")
    @GetMapping("/profile")
    public String perfil(ModelMap model, HttpSession session){
        User user = (User) session.getAttribute("userSession");
        model = userService.getUserData(session, model);
//        Image image = user.getImage();
        UserDTO userDTO = new UserDTO();
        PatientDTO patientDTO = new PatientDTO();
        ProfessionalDTO professionalDTO = new ProfessionalDTO();
        Patient patient = patientService.getPatientByUserId(user.getId());
        Professional professional = professionalService.getProfessionalByUserId(user.getId());

        if (patient != null) {
            model.addAttribute("patient", patient);
        } else if (professional != null) {
            model.addAttribute("professional", professional);
        } else {
            model.addAttribute("patient", null);
            model.addAttribute("professional", null);
        }

//        if (image != null) {
//            model.addAttribute("image", "notNull");
//        }
//        model.addAttribute("user", user);
        model.addAttribute("userDTO", userDTO);
        model.addAttribute("patientDTO", patientDTO);
        model.addAttribute("professionalDTO", professionalDTO);
        return "profile";
    }

    @GetMapping("/patient/appointments")
    public String adminAppointments(HttpSession session, ModelMap model) {
        model = userService.getUserData(session, model);
        model.put("page", null);
        return "appointments";
    }

    @GetMapping("/patient/getAppointment")
    public String getAppointments() {
        return "getAppointment";
    }

    @GetMapping("/history")
    public String history() {
        return "history.html";
    }

    @GetMapping("/noticias")
    public String noticias() {
        return "noticias.html";
    }
}
