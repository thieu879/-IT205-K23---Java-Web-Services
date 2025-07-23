package com.data.ss12.service;

import com.data.ss12.model.entity.Role;
import com.data.ss12.repository.RoleRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;

    @Override
    public Role findByName(String roleName) {
        return roleRepo.findByRoleName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));
    }

    @Override
    public Set<Role> getDefaultRoles() {
        return Set.of(findByName("USER"));
    }
}