package com.data.btthemss7.controller;

import com.data.btthemss7.entity.User;
import com.data.btthemss7.model.DataResponse;
import com.data.btthemss7.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<DataResponse<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(new DataResponse<>(users));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<User>> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(new DataResponse<>(user.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DataResponse<User>> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(new DataResponse<>(createdUser));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok(new DataResponse<>(updatedUser));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new DataResponse<>("User deleted successfully"));
    }

    @GetMapping("/top-buyers")
    public ResponseEntity<DataResponse<List<User>>> getTop3UsersByOrderQuantity() {
        List<User> topUsers = userService.getTop3UsersByOrderQuantity();
        return ResponseEntity.ok(new DataResponse<>(topUsers));
    }
}
