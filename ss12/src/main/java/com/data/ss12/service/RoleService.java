package com.data.ss12.service;

import com.data.ss12.model.entity.Role;
import java.util.Set;

public interface RoleService {
    Role findByName(String roleName);
    Set<Role> getDefaultRoles();
}
