package com.data.ss15.service;

import com.data.ss15.model.dto.request.LoginRequest;
import com.data.ss15.model.dto.request.RegisterRequest;
import com.data.ss15.model.dto.response.JWTResponse;

public interface AuthService{
    void register(RegisterRequest req);
    JWTResponse login(LoginRequest req);
}