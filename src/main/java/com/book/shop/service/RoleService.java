package com.book.shop.service;

import com.book.shop.models.Role;
import com.book.shop.models.RoleName;

public interface RoleService {
    Role findRole(RoleName name);
}
