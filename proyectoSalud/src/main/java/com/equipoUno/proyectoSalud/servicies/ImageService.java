package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    Image save(MultipartFile file) throws IOException;
    Image update(MultipartFile file, String id) throws IOException;
}

