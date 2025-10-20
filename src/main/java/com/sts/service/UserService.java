package com.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sts.model.User;
import com.sts.repository.UserRepository;

@Service
public class UserService 
{
    @Autowired
    private UserRepository userRepository;

    // Method 1: getAllUsers
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Method 2: createUser
    public String createUser(User user) {
        if (user.getUsername() == null || user.getPassword() == null) {
            return "Error: Username and password are required.";
        }
        userRepository.save(user);
        return "User created successfully with username: " + user.getUsername();
    }

    // Method 3: login
    public String login(String username, String password) {
        // Panggil method kustom di repository JPA
        User user = userRepository.findByUsernameAndPassword(username, password); 
        
        if (user != null) {
            return "Login successful. Welcome, " + user.getName() + "!"; 
        } else {
            return "Invalid username or password.";
        }
    }

    // Method 4: updateUser
    public String updateUser(long id, User userDetails) {
        if (userRepository.existsById(id)) {
            userDetails.setId(id);
            userRepository.save(userDetails);
            return "User ID " + id + " updated successfully.";
        } else {
            return "Error: User ID " + id + " not found.";
        }
    }
}