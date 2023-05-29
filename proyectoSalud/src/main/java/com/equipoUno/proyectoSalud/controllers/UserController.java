package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.dto.UserDTO;
import com.equipoUno.proyectoSalud.entities.User;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import com.equipoUno.proyectoSalud.servicies.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserServiceImplement userService;

    @Autowired
    public UserController(UserServiceImplement userService) {
        this.userService = userService;
    }

    @GetMapping("/delete")
    public String deleteUserById(@RequestParam String id) {
        userService.deleteUser(id);
        return "redirect:../admin/users";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable String id, @ModelAttribute("userDTO") UserDTO userDTO, HttpSession session) throws MiException {
        User userSession = (User) session.getAttribute("userSession");
        userSession = userService.updateUser(id, userDTO, userSession);
        session.setAttribute("userSession", userSession);
        return "redirect:/profile";
    }
}
