package com.asshofa.management.controller;

import com.asshofa.management.service.CloudinaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
@Tag(name = "Upload Service", description = "API Collections for Upload")
public class UploadFileController {

    private final CloudinaryService cloudinaryService;

    @PostMapping
    @Operation(
            summary = "Upload File to Cloudinary",
            description = "Mengupload sebuah file untuk disimpan di cloudinary"
    )
    public ResponseEntity<String> upload(@RequestPart(value = "gambar", required = false) MultipartFile file) throws IOException {
        String url = cloudinaryService.upload(file);
        return ResponseEntity.ok(url);
    }

}
