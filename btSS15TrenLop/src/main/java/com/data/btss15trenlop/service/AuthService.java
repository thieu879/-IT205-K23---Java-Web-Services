package com.data.btss15trenlop.service;

import com.data.btss15trenlop.model.dto.request.UserLogin;
import com.data.btss15trenlop.model.dto.request.UserRegister;
import com.data.btss15trenlop.model.dto.response.JWTResponse;
import com.data.btss15trenlop.model.entity.User;

public interface AuthService {
    JWTResponse login(UserLogin userLogin);
    User registerUser(UserRegister userRegister);
}
