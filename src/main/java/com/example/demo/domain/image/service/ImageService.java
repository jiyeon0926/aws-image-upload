package com.example.demo.domain.image.service;

import com.example.demo.domain.image.dto.ImageResDto;
import com.example.demo.domain.image.entity.Image;
import com.example.demo.domain.image.repository.ImageRepository;
import com.example.demo.global.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final S3Service s3Service;

    @Transactional
    public ImageResDto uploadImage(MultipartFile file) throws IOException {
        String contentType = file.getContentType();

        if (!contentType.startsWith("image")) {
            throw new IllegalArgumentException("이미지 파일만 업로드할 수 있습니다.");
        }

        String fileName = file.getOriginalFilename();
        String imageUrl = s3Service.uploadFileToFolder(file, "250901");

        Image image = new Image(imageUrl, fileName);
        Image savedImage = imageRepository.save(image);

        return new ImageResDto(savedImage);
    }

    @Transactional
    public List<ImageResDto> uploadImages(List<MultipartFile> files) {
        int maxSize = 5;
        if (files.size() > maxSize) {
            throw new IllegalArgumentException("이미지는 최대 " + maxSize + "개까지만 업로드할 수 있습니다.");
        }

        boolean isImage = files.stream()
                .allMatch(file -> Objects.requireNonNull(file.getContentType()).startsWith("image"));

        if (!isImage) {
            throw new IllegalArgumentException("이미지 파일만 업로드할 수 있습니다.");
        }

        return files.stream()
                .map(file -> {
                    try {
                        String fileName = file.getOriginalFilename();
                        String imageUrl = s3Service.uploadFileToFolder(file, "250901");

                        Image image = new Image(imageUrl, fileName);
                        Image savedImage = imageRepository.save(image);
                        return new ImageResDto(savedImage);
                    } catch (IOException e) {
                        throw new RuntimeException("파일 업로드 실패:" + file.getOriginalFilename());
                    }
                }).toList();
    }
}
