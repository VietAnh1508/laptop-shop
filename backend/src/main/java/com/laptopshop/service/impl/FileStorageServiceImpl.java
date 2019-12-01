package com.laptopshop.service.impl;

import com.laptopshop.exception.FileNotFoundException;
import com.laptopshop.exception.FileStorageException;
import com.laptopshop.properties.FileStorageProperties;
import com.laptopshop.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        String uploadDir = fileStorageProperties.getUploadDir();
        this.fileStorageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
    }

    @PostConstruct
    private void init() {
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException e) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", e);
        }
    }

    @Override
    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        validateFile(file, fileName);

        try {
            String newFileName = generateNewFileName(fileName);

            Path targetLocation = fileStorageLocation.resolve(newFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return newFileName;
        } catch (IOException e) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", e);
        }
    }

    private void validateFile(MultipartFile file, String fileName) {
        if (file.isEmpty()) {
            throw new FileStorageException("Failed to store empty file " + fileName);
        }

        if (fileName.contains("..")) {
            throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
        }
    }

    private String generateNewFileName(String originalFileName) {
        String newFileName = UUID.randomUUID().toString().replace("-", "");
        Optional<String> extension = getFileExtension(originalFileName);
        newFileName += extension.map(s -> ("." + s)).orElse("");
        return newFileName;
    }

    private Optional<String> getFileExtension(String fileName) {
        return Optional.ofNullable(fileName)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(fileName.lastIndexOf('.') + 1));
    }

    @Override
    public Resource loadFileAsResource(String fileName) {
        Path filePath = fileStorageLocation.resolve(fileName).normalize();
        try {
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new FileNotFoundException("File not found " + fileName, e);
        }
    }

    @Override
    public void deleteFile(String fileName) {
        Path filePath = fileStorageLocation.resolve(fileName).normalize();
        try {
            Files.delete(filePath);
        } catch (IOException e) {
            throw new FileNotFoundException("File not found " + fileName, e);
        }
    }

}
