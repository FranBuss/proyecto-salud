package com.equipoUno.proyectoSalud.utils;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class FileUtil {

    private static final int MAX_FILE_SIZE = 5 * 1024 * 1024;
    private static final List<String> CONTENT_TYPES_ALLOWED = Arrays.asList("image/jpeg", "image/png");

    public void validateFile(MultipartFile file){
//        if(file == null || file.isEmpty()){
//            throw new IllegalArgumentException("No se proporcionó ningún archivo");
//        }
        if(file.getSize() > MAX_FILE_SIZE){
            throw new IllegalArgumentException("El tamaño del archivo excede el límite permitido");
        }
        if(!CONTENT_TYPES_ALLOWED.contains(file.getContentType())){
            throw new IllegalArgumentException("El tipo de archivo no es valido");
        }
    }

    public byte[] readContentFile(MultipartFile file) throws IOException {
        return file.getBytes();
    }

}
