package com.book.shop.service;

import com.book.shop.dto.UserRegistrationRequestDto;
import com.book.shop.dto.UserResponseDto;
import com.book.shop.exceptions.RegistrationException;
import com.book.shop.mapper.UserMapper;
import com.book.shop.models.RoleName;
import com.book.shop.models.User;
import com.book.shop.repository.UserRepository;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Override
    public UserResponseDto createUser(UserRegistrationRequestDto requestDto) {
        if (userRepository.findByEmail(requestDto.getEmail()).isPresent()) {
            throw new RegistrationException("Email is already in use");
        }
        User userModel = userMapper.toModel(requestDto);
        userModel.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        userModel.setRoles(Set.of(roleService.findRole(RoleName.USER)));
        return userMapper.toDto(userRepository.save(userModel));
    }
}
