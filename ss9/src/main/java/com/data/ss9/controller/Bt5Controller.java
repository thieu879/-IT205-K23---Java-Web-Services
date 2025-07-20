package com.data.ss9.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bt5")
public class Bt5Controller {
    private static final Logger logger = LoggerFactory.getLogger(Bt5Controller.class);
    @GetMapping("/{id}")
    public ResponseEntity<?> getProduct(@PathVariable String id) {
        logger.info("Received request for product with ID: {}", id);
        try{
            if ("1".equals(id)) {
                return ResponseEntity.ok("Product found: Product 1");
            } else {
                throw new RuntimeException("Product not found");
            }
        } catch (RuntimeException e) {
            logger.error("Error fetching product with ID {}: {}", id, e.getMessage());
            return ResponseEntity.status(404).body("Product not found");
        }
    }
    @GetMapping("/error")
    public String triggerError() {
        logger.error("This is a simulated error endpoint");
        try{
            int result = 10 / 0;
            return "Kết quả: " + result;
        }catch (RuntimeException e){
            logger.error("An error occurred: {}", e.getMessage());
            return "An error occurred: " + e.getMessage();
        }
    }
}
