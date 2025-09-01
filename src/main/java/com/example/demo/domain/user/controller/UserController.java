package com.example.demo.domain.user.controller;

import com.example.demo.domain.user.dto.UserReqDto;
import com.example.demo.domain.user.dto.UserResDto;
import com.example.demo.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResDto> signup(@RequestPart("request") UserReqDto userReqDto,
                                             @RequestPart("files") List<MultipartFile> files) {
        UserResDto userResDto = userService.signup(userReqDto.getName(), files);

        return new ResponseEntity<>(userResDto, HttpStatus.CREATED);
    }
}
