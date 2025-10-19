package com.sts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sts.model.User;
import com.sts.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
    // ... KODE ANDA YANG SEBELUMNYA SUDAH BENAR ...
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.login(user.getUsername(), user.getPassword());
    }

    @PutMapping("/{id}")
    public String updateProfile(@PathVariable long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }
}