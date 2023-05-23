package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.servicies.PatientServiceImplement;
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

    @Autowired
    public PatientController(PatientServiceImplement patientService){
        this.patientService = patientService;
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
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable String id, @RequestBody PatientDTO patientDTO) {
        PatientDTO updatedPatient = patientService.updatePatient(id, patientDTO);
        if (updatedPatient != null) {
            return ResponseEntity.ok(updatedPatient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/delete/{id}", params = "_method=delete")
    public ResponseEntity<Void> deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping ("/allPatients")
    public String listPatients(ModelMap model) {
        List<PatientDTO> patients = patientService.findAllPatients();
        model.addAttribute("patients", patients);
        return "patient_list";
    }

}
