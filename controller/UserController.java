package com.example.sns_api.controller;

import com.example.sns_api.model.User;
import com.example.sns_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // GET http://localhost:8080/api/users で全員取得
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // POST http://localhost:8080/api/users でユーザー登録
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
