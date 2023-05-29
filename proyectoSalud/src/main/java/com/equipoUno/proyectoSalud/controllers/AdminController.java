package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.servicies.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserServiceImplement userServiceImplement;

    @Autowired
    public AdminController(UserServiceImplement userServiceImplement) {
        this.userServiceImplement = userServiceImplement;
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard.html";
    }



}
