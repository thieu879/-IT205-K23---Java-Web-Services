package com.data.ss1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Bt2 {
    @GetMapping("/hello")
    public String hello() {
        return "bt1/hello";
    }
}
