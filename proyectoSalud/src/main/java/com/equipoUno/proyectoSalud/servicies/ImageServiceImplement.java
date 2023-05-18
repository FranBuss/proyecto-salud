//package com.equipoUno.proyectoSalud.servicies;
//
//import com.equipoUno.proyectoSalud.entities.Image;
//import com.equipoUno.proyectoSalud.exceptions.MiException;
//import com.equipoUno.proyectoSalud.repositories.ImageRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.Optional;
//
//@Service
//public class ImageServiceImplement {
//
//    @Autowired
//    private ImageRepository imageRepository;
//
//    public Image save(MultipartFile file) throws MiException {
//        if (file != null) {
//            try {
//
//                Image image = new Image();
//                image.setMime(file.getContentType());
//                image.setName(file.getName());
//                image.setContent(file.getBytes());
//
//                return imageRepository.save(image);
//
//            } catch (Exception e) {
//
//                System.err.println(e.getMessage());
//
//            }
//        }
//
//        return null;
//    }
//
//    public Image update(MultipartFile file, String idImage) throws MiException {
//
//        if (file != null) {
//            try {
//
//                Image image = new Image();
//
//                if (idImage != null) {
//                    Optional<Image> answer = imageRepository.findById(idImage);
//
//                    if (answer.isPresent()) {
//                        image = answer.get();
//                    }
//                }
//
//                image.setMime(file.getContentType());
//                image.setName(file.getName());
//                image.setContent(file.getBytes());
//
//                return imageRepository.save(image);
//
//            } catch (Exception e) {
//
//                System.err.println(e.getMessage());
//
//            }
//        }
//
//        return null;
//    }
//
//}
