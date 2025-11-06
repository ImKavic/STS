package com.sts.controller;

import com.sts.model.LoginHistory;
import com.sts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/history")
public class LoginHistoryController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<List<LoginHistory>> getHistoryByUserId(@PathVariable Long userId) {
        List<LoginHistory> history = userService.getLoginHistory(userId);
        
        if (history.isEmpty()) {
            // 204 No Content
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
        } else {
            // 200 OK
            return new ResponseEntity<>(history, HttpStatus.OK);
        }
    }
}