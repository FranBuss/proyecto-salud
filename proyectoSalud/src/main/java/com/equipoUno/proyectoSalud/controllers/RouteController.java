package com.equipoUno.proyectoSalud.controllers;


import com.equipoUno.proyectoSalud.enumerations.Specialization;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class RouteController {

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/searcher")
    public String searcher(Model model,  @RequestParam Map<String, String> queryParams){
        Specialization[] specializations = Specialization.values();
        model.addAttribute("specializations",specializations);
        return "searcher";
    }

}
