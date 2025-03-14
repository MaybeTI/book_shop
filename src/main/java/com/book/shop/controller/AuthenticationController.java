package com.book.shop.controller;

import com.book.shop.dto.UserRegistrationRequestDto;
import com.book.shop.dto.UserResponseDto;
import com.book.shop.exceptions.RegistrationException;
import com.book.shop.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/registration")
    public UserResponseDto register(
            @RequestBody @Valid UserRegistrationRequestDto requestDto
    ) throws RegistrationException {
        return userService.createUser(requestDto);
    }
}
