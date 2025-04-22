package com.example.backend_spaces.controllers;

import com.example.backend_spaces.dto.LoginRequest;
import com.example.backend_spaces.dto.RegisterRequest;
import com.example.backend_spaces.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private AuthService authService;

    // API đăng ký tài khoản mới
    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody RegisterRequest request) {
        try {
            // Đăng ký tài khoản mới
            String message = authService.register(request.getEmail(), request.getPassword(), request.getRole(), request.getGymId());
            return ResponseEntity.ok(message);  // Trả về thông báo thành công
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());  // Trả về lỗi nếu email đã tồn tại
        }
    }

    // API đăng nhập
    @PostMapping("/account")
    public ResponseEntity<?> loginAccount(@RequestBody LoginRequest request) {
        return authService.login(request.getEmail(), request.getPassword())
                .map(token -> ResponseEntity.ok("Bearer " + token))  // Trả về token dưới dạng Bearer token
                .orElse(ResponseEntity.status(401).body("Email hoặc mật khẩu không đúng"));
    }
}
