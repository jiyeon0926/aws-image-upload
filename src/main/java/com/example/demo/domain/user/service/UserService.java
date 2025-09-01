package com.example.demo.domain.user.service;

import com.example.demo.domain.image.dto.ImageResDto;
import com.example.demo.domain.image.service.ImageService;
import com.example.demo.domain.user.dto.UserResDto;
import com.example.demo.domain.user.entity.User;
import com.example.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ImageService imageService;

    @Transactional
    public UserResDto signup(String name, List<MultipartFile> files) {
        User user = new User(name);
        User savedUser = userRepository.save(user);
        List<ImageResDto> imageResDtos = imageService.uploadImages(files);

        return new UserResDto(savedUser, imageResDtos);
    }
}
