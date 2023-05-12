package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.servicies.PatientServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
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

    //Comment prueba
    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO) {
        PatientDTO createdPatient = patientService.createPatient(patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPatient);
    }

    @PostMapping(value = "/patients/{id}", params = "_method=put")
    public ResponseEntity<PatientDTO> updatePatient(@PathVariable String id, @RequestBody PatientDTO patientDTO) {
        patientDTO.setId(id);
        PatientDTO updatedPatient = patientService.updatePatient(patientDTO);
        if (updatedPatient != null) {
            return ResponseEntity.ok(updatedPatient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/patients/{id}", params = "_method=delete")
    public ResponseEntity<Void> deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }


}
