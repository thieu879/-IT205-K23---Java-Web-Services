package com.data.ss14;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class Ss14Application {

    public static void main(String[] args) {
        SpringApplication.run(Ss14Application.class, args);
    }

}

