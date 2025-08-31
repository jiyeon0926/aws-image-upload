package com.example.demo.domain.image.controller;

import com.example.demo.domain.image.dto.ImageResDto;
import com.example.demo.domain.image.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    public ResponseEntity<ImageResDto> uploadImage(@RequestParam("file") MultipartFile file) {
        ImageResDto imageResDto = imageService.uploadImage(file);

        return ResponseEntity.ok(imageResDto);
    }
}
