package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.entities.Image;
import com.equipoUno.proyectoSalud.exceptions.MiException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public interface ImageService {

    Image save(MultipartFile file) throws MiException;
    Image update(MultipartFile file, String idImage) throws MiException;
}
