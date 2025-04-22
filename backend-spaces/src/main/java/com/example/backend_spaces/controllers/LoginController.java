package com.example.backend_spaces.controllers;

import java.util.Optional;

import com.example.backend_spaces.dto.LoginRequest;
import com.example.backend_spaces.model.UserAccount;
import com.example.backend_spaces.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private AuthService authService;

    @PostMapping("/account")
    public ResponseEntity<?> loginAccount(@RequestBody LoginRequest request) {
        Optional<UserAccount> user = authService.login(request.getEmail(), request.getPassword());
    
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get()); // Nếu có UserAccount, trả về 200 OK
        } else {
            return ResponseEntity.status(401).body("Email hoặc mật khẩu không đúng"); // Nếu không có, trả về lỗi 401
        } 
    }
}
