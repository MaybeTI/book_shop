package com.book.shop.mapper;

import com.book.shop.config.MapperConfig;
import com.book.shop.dto.UserRegistrationRequestDto;
import com.book.shop.dto.UserResponseDto;
import com.book.shop.models.User;
import org.mapstruct.Mapper;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserResponseDto toDto(User user);

    User toModel(UserRegistrationRequestDto userDto);
}
