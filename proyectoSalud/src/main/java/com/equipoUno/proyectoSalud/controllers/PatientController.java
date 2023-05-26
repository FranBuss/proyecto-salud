package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.entities.Patient;
import com.equipoUno.proyectoSalud.servicies.PatientServiceImplement;
import com.equipoUno.proyectoSalud.servicies.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientServiceImplement patientService;
    private final UserServiceImplement userService;

    @Autowired
    public PatientController(PatientServiceImplement patientService, UserServiceImplement userService){
        this.patientService = patientService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable String id) {
        PatientDTO patientDTO = patientService.getPatient(id);
        if (patientDTO != null) {
            return ResponseEntity.ok(patientDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/createPatient")
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        PatientDTO createdPatient = patientService.createPatient(patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
    }

    @PostMapping(value = "/update/{id}", params = "_method=put")
    public String updatePatient(@PathVariable String id) {
        Patient updatedPatient = patientService.updatePatient(id);
        if (updatedPatient != null) {
            return "/professionals/patients";
        } else {
            return "/error";
        }
    }

    @PostMapping(value = "/delete/{id}", params = "_method=delete")
    public String deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return "/professionals/patients";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping ("/allPatients")
    public String listPatients(ModelMap model) {
        List<PatientDTO> patients = patientService.findAllPatients();
        model.addAttribute("patients", patients);
        return "patient_list";
    }

    @PostMapping("/{userId}/patients")
    public ResponseEntity<String> assignPatientUser(@PathVariable String userId, @RequestBody PatientDTO patientDTO) {
        userService.assignPatientUser(userId, patientDTO);
        return ResponseEntity.ok("Paciente asignado correctamente");
    }

}
