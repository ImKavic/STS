package com.sts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sts.dao.UserDao;
import com.sts.model.User;
import com.sts.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
        private UserRepository userRepository; // <-- Tambahkan injeksi ini

    // Method 1: getAllUsers - Mengembalikan List<User>
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    // Method 2: createUser - Mengembalikan String
    public String createUser(User user) {
        // KODE DIKEMBALIKAN: Cek data input yang wajib
        if (user.getUsername() == null || user.getPassword() == null) {
            return "Error: Username and password are required.";
        }
        userDao.save(user); 
        // KODE DIKEMBALIKAN: Menggunakan getUsername()
        return "User created successfully with username: " + user.getUsername();
    }

    // Method 3: login - Mengembalikan String (status/token)
    public String login(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, password);
        
        if (user != null) {
            // KODE DIKEMBALIKAN: Menggunakan getName()
            return "Login successful. Welcome, " + user.getName() + "!"; 
        } else {
            return "Invalid username or password.";
        }
    }

    // Method 4: updateUser - Mengembalikan String
    public String updateUser(long id, User userDetails) {
        // Cek apakah User dengan ID tersebut ada sebelum diupdate
        // ... (Logika validasi bisa ditambahkan di sini)
        
        userDao.update(id, userDetails);
        return "User ID " + id + " updated successfully.";
    }
}