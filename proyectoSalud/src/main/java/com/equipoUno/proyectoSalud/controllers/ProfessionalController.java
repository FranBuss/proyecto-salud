package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.dto.ProfessionalDTO;
import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.servicies.ProfessionalService;
import com.equipoUno.proyectoSalud.servicies.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("896656")
public class ProfessionalController {

    private final ProfessionalService professionalService;
    private final UserServiceImplement userService;

    @Autowired
    public ProfessionalController(ProfessionalService professionalService, UserServiceImplement userService) {
        this.professionalService = professionalService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessionalDTO> getPatient(@PathVariable String id) throws MiException {
        ProfessionalDTO professionalDTO = professionalService.getProfessional(id);
        if (professionalDTO != null) {
            return ResponseEntity.ok(professionalDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // List all Professionals
    @GetMapping("/allProfessionals")
    public ResponseEntity<List<Professional>> listProfessionals() throws MiException {
        List<Professional> professionals = professionalService.searchProfessionals();
        if (professionals != null) {
            return ResponseEntity.ok(professionals);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Create a Professional
//    @PostMapping("/create")
//    public ResponseEntity<ProfessionalDTO> create(@RequestBody ProfessionalDTO professionalDTO) {
//        ProfessionalDTO professionalCreate = professionalService.createProfessional(professionalDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(professionalCreate);
//    }

    @GetMapping("/create")
    public String professionalRegister(){
        return "professional_form";
    }

    @PostMapping("/create")
    public String create(@RequestParam ProfessionalDTO professionalDTO, ModelMap model){
        try {

            professionalService.createProfessional(professionalDTO);
            model.put("success", "The professional has been created correctly");

        } catch (MiException ex){

            model.put("error", ex.getMessage());
            return "professional_form";

        }
        return "index";
    }


    //Update a Professional
    @PostMapping(value = ("/update/{id}"), params = "_method=put")
    public String update(@RequestBody @PathVariable String id) throws MiException {
        Professional professionalUpdate = professionalService.updateProfessional(id);
        if (professionalUpdate != null) {
            return "/admin/professionals";
        } else {
            return "/error";
        }
    }


    @PostMapping(value = ("/delete/{id}"), params = "_method=delete")
    public String delete(@RequestBody @PathVariable String id) throws MiException{
        professionalService.deleteProfessional(id);
        return "/admin/professionals";
    }

    @PostMapping(value = "/updateDropOut/{id}", params = "_method=put")
    public ResponseEntity<ProfessionalDTO> updateDropOut(@PathVariable String id)  throws MiException {
        ProfessionalDTO professionalUpdate = professionalService.updateDropOut(id);
        if (professionalUpdate != null) {
            return ResponseEntity.ok(professionalUpdate);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/changeRol/{userId}")
    public String assignProfessionalUser(@PathVariable String userId, @RequestBody ProfessionalDTO professionalDTO) {
        userService.assignProfessionalUser(userId, professionalDTO);
        return "redirect:../admin/users";
    }


}
