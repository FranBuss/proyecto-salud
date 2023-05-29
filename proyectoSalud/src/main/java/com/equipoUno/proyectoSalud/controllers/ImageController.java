package com.equipoUno.proyectoSalud.controllers;

import com.equipoUno.proyectoSalud.entities.User;
import com.equipoUno.proyectoSalud.servicies.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    UserServiceImplement userService;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> profileImage(@PathVariable String id) {
        User user = userService.getOne(id);
        byte[] image = user.getImage().getContent();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(image, headers, HttpStatus.OK);
    }
}