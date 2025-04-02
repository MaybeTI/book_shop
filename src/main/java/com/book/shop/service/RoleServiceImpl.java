package com.book.shop.service;

import com.book.shop.exceptions.EntityNotFoundException;
import com.book.shop.models.Role;
import com.book.shop.models.RoleName;
import com.book.shop.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role findRole(RoleName name) {
        return roleRepository.findByName(name).orElseThrow(
                () -> new EntityNotFoundException("Not found role with name: " + name)
        );
    }
}
