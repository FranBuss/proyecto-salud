package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.dto.ProfessionalDTO;
import com.equipoUno.proyectoSalud.servicies.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professionals")
public class ProfessionalController {

    private final ProfessionalService professionalService;

    @Autowired
    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @GetMapping
    public ResponseEntity<List<ProfessionalDTO>> getAllProfessionals(){
        List<ProfessionalDTO> professionals = professionalService.getAllProfessionals();
        return ResponseEntity.ok(professionals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalDTO> getProfessionalById(@PathVariable("id") String id) {
        ProfessionalDTO professional = professionalService.getProfessionalById(id);
        if (professional != null) {
            return ResponseEntity.ok(professional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //Revisar esta parte de codigo que no estaria funcionando
    @PostMapping("/")
    public ResponseEntity<ProfessionalDTO> createProfessional(@RequestBody ProfessionalDTO professionalDTO){
        ProfessionalDTO createdProfessional = professionalService.createProfessional(professionalDTO);
        if (createdProfessional != null) {
            return new ResponseEntity<>(createdProfessional, HttpStatus.CREATED);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessionalDTO> updateProfessional(@PathVariable("id") String id, @RequestBody ProfessionalDTO professionalDTO){
        ProfessionalDTO updatedProfessional = professionalService.updateProfessional(id, professionalDTO);
        if (updatedProfessional != null) {
            return ResponseEntity.ok(updatedProfessional);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessional(@PathVariable("id") String id) {
        boolean deleted = professionalService.deleteProfessional(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
