package com.data.ss12.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bt1")
public class Bt1Controller {
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello");
    }
}
//Lợi Ích Của Việc Sử Dụng Spring Security:

//Tích hợp liền mạch với Spring ecosystem
//Cấu hình linh hoạt thông qua annotations và configuration classes
//Bảo mật mặc định - secure by default principle
//Khả năng mở rộng cao với custom implementations
//Cộng đồng lớn và documentation phong phú