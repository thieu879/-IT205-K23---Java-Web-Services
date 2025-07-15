package com.data.ss6.controller;

import com.data.ss6.entity.User;
import com.data.ss6.model.DataResponse;
import com.data.ss6.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public ResponseEntity<DataResponse<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(new DataResponse<>(users, org.springframework.http.HttpStatus.OK));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<User>> getUserById(Long id) {
        User user = userService.getUserById(id);
        if (user != null) {
            return ResponseEntity.ok(new DataResponse<>(user, org.springframework.http.HttpStatus.OK));
        } else {
            return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND)
                    .body(new DataResponse<>(null, org.springframework.http.HttpStatus.NOT_FOUND));
        }
    }
    @PostMapping
    public ResponseEntity<DataResponse<User>> createUser(User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.status(org.springframework.http.HttpStatus.CREATED)
                .body(new DataResponse<>(createdUser, org.springframework.http.HttpStatus.CREATED));
    }
    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return ResponseEntity.ok(new DataResponse<>(updatedUser, org.springframework.http.HttpStatus.OK));
        } else {
            return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND)
                    .body(new DataResponse<>(null, org.springframework.http.HttpStatus.NOT_FOUND));
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<Void>> deleteUser(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted) {
            return ResponseEntity.ok(new DataResponse<>(null, org.springframework.http.HttpStatus.OK));
        } else {
            return ResponseEntity.status(org.springframework.http.HttpStatus.NOT_FOUND)
                    .body(new DataResponse<>(null, org.springframework.http.HttpStatus.NOT_FOUND));
        }
    }
}
