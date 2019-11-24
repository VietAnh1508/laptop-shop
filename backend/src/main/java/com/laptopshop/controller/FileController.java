package com.laptopshop.controller;

import com.laptopshop.payload.UploadFileResponse;
import com.laptopshop.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @Value("${api.path.prefix}")
    private String apiPathPrefix;

    private Logger logger = LoggerFactory.getLogger(FileController.class);

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        logger.info("Upload file {}", fileName);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(apiPathPrefix + "/downloadFile/")
                .path(fileName)
                .toUriString();

        return new UploadFileResponse(
                fileName,
                fileDownloadUri,
                file.getContentType(),
                file.getSize()
        );
    }

    @PostMapping(value = "/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFile(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        logger.info("Download file {}", fileName);

        Resource resource = fileStorageService.loadFileAsResource(fileName);

        String contentType = "application/octet-stream";
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            logger.error("Could not determine file type");
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @DeleteMapping("/deleteFile/{fileName:.+}")
    public ResponseEntity deleteFile(@PathVariable String fileName) {
        logger.info("Delete file {}", fileName);

        fileStorageService.deleteFile(fileName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
