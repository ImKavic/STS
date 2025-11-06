package com.sts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "Users")
public class User {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id;

    @Column(unique = true, nullable = false, length = 50) 
    private String username;
    
    @Column(nullable = false) 
    private String password;
    
    @Column(nullable = false, length = 100) 
    private String name;
    
    @Column(name = "phoneNumber", length = 15) 
    private String phoneNumber;

    // --- CONSTRUCTOR ---
    public User() {}

    public User(long id, String username, String password, String name, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    // --- GETTER DAN SETTER ---
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}