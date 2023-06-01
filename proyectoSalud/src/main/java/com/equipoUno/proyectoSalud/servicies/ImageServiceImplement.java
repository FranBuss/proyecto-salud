package com.equipoUno.proyectoSalud.servicies;

import com.equipoUno.proyectoSalud.entities.Image;
import com.equipoUno.proyectoSalud.repositories.ImageRepository;
import com.equipoUno.proyectoSalud.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageServiceImplement implements ImageService{

    private final ImageRepository imageRepository;
    private final FileUtil fileUtil;

    @Autowired
    public ImageServiceImplement(ImageRepository imageRepository, FileUtil fileUtil){
        this.imageRepository = imageRepository;
        this.fileUtil =  fileUtil;
    }

    @Override
    public Image save(MultipartFile file) throws IOException {
        fileUtil.validateFile(file);
        byte[] content = fileUtil.readContentFile(file);
        Image image = createImage(file.getContentType(), file.getName(), content);
        return imageRepository.save(image);
    }

    public Image createImage(String contentType, String name, byte[] content){
        Image image = new Image();
        image.setContentType(contentType);
        image.setName(name);
        image.setContent(content);

        return image;
    }

    @Override
    public Image update(MultipartFile file, String id) throws IOException {
        if (file != null) {
            fileUtil.validateFile(file);
        }
        byte[] content = fileUtil.readContentFile(file);
        Optional<Image> imageOptional = imageRepository.findById(id);
        Image image = imageOptional.orElseGet(Image::new);
        image.setContentType(file.getContentType());
        image.setName(file.getName());
        image.setContent(content);

        return imageRepository.save(image);
    }
}
