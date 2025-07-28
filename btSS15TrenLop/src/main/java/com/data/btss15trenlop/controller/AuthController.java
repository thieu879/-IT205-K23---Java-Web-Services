package com.data.btss15trenlop.controller;

import com.data.btss15trenlop.model.dto.request.UserLogin;
import com.data.btss15trenlop.model.dto.request.UserRegister;
import com.data.btss15trenlop.model.dto.response.APIResponse;
import com.data.btss15trenlop.model.dto.response.JWTResponse;
import com.data.btss15trenlop.model.entity.User;
import com.data.btss15trenlop.service.AuthService;
import com.data.btss15trenlop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;



    @PostMapping
    public ResponseEntity<APIResponse<User>> registerUser(@RequestBody UserRegister  userRegister){
        return new ResponseEntity<>(new APIResponse<>(true,"Regiser user successfully!",authService.registerUser(userRegister), HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<JWTResponse>> login(@RequestBody UserLogin userLogin){
        return new ResponseEntity<>(new APIResponse<>(true,"Login successfully!",authService.login(userLogin), HttpStatus.OK),HttpStatus.OK);
    }

}
