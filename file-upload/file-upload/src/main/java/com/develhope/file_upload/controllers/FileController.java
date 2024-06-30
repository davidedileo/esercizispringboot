package com.develhope.file_upload.controllers;

import com.develhope.file_upload.services.FileStorageService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    private final FileStorageService fileStorageService;

    @Autowired
    public FileController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/download")
    public byte[] download(@RequestParam String fileName, HttpServletResponse response) throws IOException {
        String extension = FilenameUtils.getExtension(fileName);
        switch (extension) {
            case "gif":
                response.setContentType(MediaType.IMAGE_GIF_VALUE);
                break;
            case "jpg", "jpeg":
                response.setContentType(MediaType.IMAGE_JPEG_VALUE);
                break;
            case "png":
                response.setContentType(MediaType.IMAGE_PNG_VALUE);
                break;
            default:
                break;
        }
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        return fileStorageService.download(fileName);
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }
        return fileStorageService.upload(file);
    }

    @PostMapping("/multiUpload")
    public List<String> upload(@RequestParam MultipartFile[] files) throws IOException {
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            String singleUploadedFileName = fileStorageService.upload(file);
            fileNames.add(singleUploadedFileName);
        }
        return fileNames;
    }

}
