package com.example.demo.domain.user.dto;

import com.example.demo.domain.image.dto.ImageResDto;
import com.example.demo.domain.user.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class UserResDto {

    private final Long id;
    private final String name;
    private final LocalDateTime createdAt;
    private final List<ImageResDto> images;

    public UserResDto(User user, List<ImageResDto> images) {
        this.id = user.getId();
        this.name = user.getName();
        this.createdAt = user.getCreatedAt();
        this.images = images;
    }
}
