package com.data.ss12.service;

import com.data.ss12.model.entity.User;

public interface UserService {
    User register(String username, String password);
    User login(String username, String password);
    User findByUsername(String username);
}