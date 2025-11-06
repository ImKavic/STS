package com.sts.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LoginHistory")
public class LoginHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private LocalDateTime loginTime;

    @Column
    private String clientInfo; 

    public LoginHistory() {
    }

    public LoginHistory(Long userId, String clientInfo) {
        this.userId = userId;
        this.loginTime = LocalDateTime.now();
        this.clientInfo = clientInfo;
    }

    // --- GETTER DAN SETTER ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public String getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(String clientInfo) {
        this.clientInfo = clientInfo;
    }
}