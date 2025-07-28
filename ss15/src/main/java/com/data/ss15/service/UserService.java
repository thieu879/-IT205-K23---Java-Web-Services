package com.data.ss15.service;

import com.data.ss15.model.entity.User;

public interface UserService {
    void updateUserRole(Long userId, String newRole);
    User getCurrentUser();
}