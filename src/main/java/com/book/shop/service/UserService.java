package com.book.shop.service;

import com.book.shop.dto.UserRegistrationRequestDto;
import com.book.shop.dto.UserResponseDto;

public interface UserService {
    UserResponseDto createUser(UserRegistrationRequestDto requestDto);
}
