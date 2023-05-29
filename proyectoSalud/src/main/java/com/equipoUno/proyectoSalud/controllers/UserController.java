package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.servicies.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserServiceImplement userService;

    @Autowired
    public UserController(UserServiceImplement userService){
        this.userService = userService;
    }

    @GetMapping("/delete")
    public String deleteUserById(@RequestParam String id){
        userService.deleteUser(id);
        return "redirect:../admin/users";
    }
}
