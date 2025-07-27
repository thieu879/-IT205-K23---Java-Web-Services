package com.data.ss14.service;

import com.data.ss14.model.dto.request.UserLogin;
import com.data.ss14.model.dto.response.JWTResponse;

public interface AuthService {
    JWTResponse login(UserLogin userLogin);
}