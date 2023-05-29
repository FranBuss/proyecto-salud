package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.dto.ProfessionalDTO;
import com.equipoUno.proyectoSalud.entities.Professional;
import com.equipoUno.proyectoSalud.enumerations.Specialization;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.servicies.ProfessionalService;
import com.equipoUno.proyectoSalud.servicies.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/professional")
public class ProfessionalController {

    private final ProfessionalService professionalService;
    private final UserServiceImplement userService;

    @Autowired
    public ProfessionalController(ProfessionalService professionalService, UserServiceImplement userService) {
        this.professionalService = professionalService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String getProfessional(@PathVariable String id, ModelMap model) {
        ProfessionalDTO professionalDTO = professionalService.getProfessional(id);
        if (professionalDTO != null){
            model.put("professionalDTO", professionalDTO);
            return "professional_view";
        }
        return null;
    }


    // Create a Professional
//    @PostMapping("/create")
//    public ResponseEntity<ProfessionalDTO> create(@RequestBody ProfessionalDTO professionalDTO) {
//        ProfessionalDTO professionalCreate = professionalService.createProfessional(professionalDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(professionalCreate);
//    }

//    @GetMapping("/create")
//    public String professionalRegister(){
//        return "professional_form";
//    }
//
//    @PostMapping("/create")
//    public String create(@RequestParam ProfessionalDTO professionalDTO, ModelMap model){
//        try {
//
//            professionalService.createProfessional(professionalDTO);
//            model.put("success", "The professional has been created correctly");
//
//        } catch (MiException ex){
//
//            model.put("error", ex.getMessage());
//            return "professional_form";
//
//        }
//        return "index";
//    }


    //Update a Professional
    @PostMapping(value = ("/update/{id}"))
    public String update(@RequestBody @PathVariable String id) throws MiException {
        Professional professionalUpdate = professionalService.updateProfessional(id);
        if (professionalUpdate != null) {
            return "/admin/professionals";
        } else {
            return "/error";
        }
    }


    @PostMapping(value = ("/delete/{id}"))
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

    @GetMapping("/changeRolToProfessional/{userId}")
    public String assignProfessionalUser(@PathVariable String userId, ModelMap model){
        model.put("user", userService.getOne(userId));
        ProfessionalDTO professionalDTO = new ProfessionalDTO();
        model.put("professionalDTO", professionalDTO);
        Specialization[] specializations = Specialization.values();
        model.addAttribute("specializations", specializations);
        return "professional_form";
    }

    @PostMapping("/changeRol/{userId}")
    public String assignProfessionalUser(@PathVariable("userId") String userId,@ModelAttribute("professionalDTO") ProfessionalDTO professionalDTO) {
        userService.assignProfessionalUser(userId, professionalDTO);
        return "redirect:../admin/users";
    }


}
