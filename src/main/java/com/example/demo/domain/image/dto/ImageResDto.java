package com.example.demo.domain.image.dto;

import com.example.demo.domain.image.entity.Image;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ImageResDto {

    private final Long id;
    private final String imageUrl;
    private final String fileName;

    public ImageResDto(Image image) {
        this.id = image.getId();
        this.imageUrl = image.getImageUrl();
        this.fileName = image.getFileName();
    }
}
