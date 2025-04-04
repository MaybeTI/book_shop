package com.book.shop.controller;

import com.book.shop.dto.UserLoginRequestDto;
import com.book.shop.dto.UserLoginResponseDto;
import com.book.shop.dto.UserRegistrationRequestDto;
import com.book.shop.dto.UserResponseDto;
import com.book.shop.exceptions.RegistrationException;
import com.book.shop.security.AuthenticationService;
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
    private final AuthenticationService authenticationService;

    @PostMapping("/registration")
    public UserResponseDto register(
            @RequestBody @Valid UserRegistrationRequestDto requestDto
    ) throws RegistrationException {
        return userService.createUser(requestDto);
    }

    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto request) {
        return authenticationService.authenticate(request);
    }
}
