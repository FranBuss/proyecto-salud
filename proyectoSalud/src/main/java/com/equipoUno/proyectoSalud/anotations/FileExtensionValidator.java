package com.equipoUno.proyectoSalud.anotations;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class FileExtensionValidator implements ConstraintValidator<FileExtension, MultipartFile> {
    private String[] allowedExtensions;
    private String errorMessage;

    @Override
    public void initialize(FileExtension constraintAnnotation) {
        allowedExtensions = constraintAnnotation.value();
        errorMessage = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return true; // Permite archivos nulos o vacíos
        }

        String fileExtension = getFileExtension(file.getOriginalFilename());
        boolean isValid = Arrays.stream(allowedExtensions).anyMatch(extension -> extension.equalsIgnoreCase(fileExtension));

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
        }

        return isValid;
    }

    private String getFileExtension(String filename) {
        if (filename == null || filename.isEmpty()) {
            return "";
        }

        int extensionIndex = filename.lastIndexOf(".");
        if (extensionIndex >= 0) {
            return filename.substring(extensionIndex + 1);
        }

        return "";
    }
}
