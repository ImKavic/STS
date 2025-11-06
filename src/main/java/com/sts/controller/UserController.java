package com.sts.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sts.model.LoginResponse;
import com.sts.model.SimpleApiResponse;
import com.sts.model.User;
import com.sts.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
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
    public ResponseEntity<LoginResponse> login(@RequestBody User user) {
        LoginResponse response = userService.login(user.getUsername(), user.getPassword());
        
        if (response.isSuccess()) {
            // 200 OK
            return new ResponseEntity<>(response, HttpStatus.OK); 
        } else {
            // 401 Unauthorized
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED); 
        }
    }

    @PutMapping("/{id}")
    public String updateProfile(@PathVariable long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getProfile(@PathVariable long id) {
        User user = userService.findUserById(id);
        
        if (user != null) {
            user.setPassword(null);
            // 200 OK
            return new ResponseEntity<>(user, HttpStatus.OK); 
        } else {
            // 404 Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SimpleApiResponse> deleteUser(@PathVariable long id) {
        SimpleApiResponse response = userService.deleteUser(id);

        if (response.isSuccess()) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); 
        }
    }
}