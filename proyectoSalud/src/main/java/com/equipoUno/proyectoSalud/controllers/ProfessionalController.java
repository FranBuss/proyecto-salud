package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.dto.PatientDTO;
import com.equipoUno.proyectoSalud.dto.ProfessionalDTO;
import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.enumerations.Specialization;
import com.equipoUno.proyectoSalud.servicies.ProfessionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professionals")
public class ProfessionalController {

    private final ProfessionalService professionalService;

    @Autowired
    public ProfessionalController(ProfessionalService professionalService) {
        this.professionalService = professionalService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalDTO> getPatient(@PathVariable String id) {
        ProfessionalDTO professionalDTO = professionalService.getProfessional(id);
        if (professionalDTO != null) {
            return ResponseEntity.ok(professionalDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //List all Professionals
    @GetMapping("/allProfessionals")
    public ResponseEntity<List<Professional>> listProfessionals() {
        List<Professional> professionals = professionalService.searchProfessionals();
        if (professionals != null) {
            return ResponseEntity.ok(professionals);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Create a Professional
    @PostMapping("/create")
    public ResponseEntity<ProfessionalDTO> create(@RequestBody ProfessionalDTO professionalDTO) {
        ProfessionalDTO professionalCreate = professionalService.createProfessional(professionalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(professionalCreate);
    }

    //Update a Professional
    @PostMapping(value = ("/update/{id}"), params = "_method=put")
    public String update(@RequestBody @PathVariable String id) {
        Professional professionalUpdate = professionalService.updateProfessional(id);
        if (professionalUpdate != null) {
            return "/admin/professionals";
        } else {
            return "/error";
        }
    }

    @PostMapping(value = ("/delete/{id}"), params = "_method=delete")
    public String delete(@RequestBody @PathVariable String id) {
        professionalService.deleteProfessional(id);
        return "/admin/professionals";
    }

    @PostMapping(value = "/updateDropOut/{id}", params = "_method=put")
    public ResponseEntity<ProfessionalDTO> updateDropOut(@PathVariable String id) {
        ProfessionalDTO professionalUpdate = professionalService.updateDropOut(id);
        if (professionalUpdate != null) {
            return ResponseEntity.ok(professionalUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
