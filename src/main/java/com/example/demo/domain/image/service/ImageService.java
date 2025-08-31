package com.example.demo.domain.image.service;

import com.example.demo.domain.image.dto.ImageResDto;
import com.example.demo.domain.image.entity.Image;
import com.example.demo.domain.image.repository.ImageRepository;
import com.example.demo.global.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final S3Service s3Service;

    @Transactional
    public ImageResDto uploadImage(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String imageUrl = s3Service.uploadFileToFolder(file, "250901");

        Image image = new Image(imageUrl, fileName);
        Image savedImage = imageRepository.save(image);

        return new ImageResDto(savedImage);
    }
}
