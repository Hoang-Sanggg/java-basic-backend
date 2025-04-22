package com.example.backend_spaces.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_spaces.model.UserAccount;
import com.example.backend_spaces.service.AuthService;

@RestController
@RequestMapping("/api/auth")  // Đảm bảo đường dẫn đúng
public class AuthController {

    @Autowired
    private AuthService authService;

    // Đăng ký người dùng mới
    @PostMapping("/register")  // Đảm bảo đường dẫn đúng
    public ResponseEntity<String> register(@RequestBody UserAccount userAccount) {
        if (userAccount.getEmail() == null || userAccount.getPassword() == null || userAccount.getRole() == null || userAccount.getGymId() == null) {
            return ResponseEntity.status(400).body("Thiếu tham số bắt buộc");
        }

        try {
            // Gọi service đăng ký
            authService.register(userAccount.getEmail(), userAccount.getPassword(), userAccount.getRole(), userAccount.getGymId());
            return ResponseEntity.ok("Đăng ký thành công");
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(e.getMessage());  // Trả về lỗi nếu có
        }
    }

    // Đăng nhập và trả về token nếu thông tin hợp lệ
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserAccount userAccount) {
        if (userAccount.getEmail() == null || userAccount.getPassword() == null) {
            return ResponseEntity.status(400).body("Thiếu email hoặc mật khẩu");
        }

        try {
            // Gọi service login để lấy token
            String token = authService.login(userAccount.getEmail(), userAccount.getPassword());
            return ResponseEntity.ok("Bearer " + token);  // Trả về token với định dạng Bearer token
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());  // Trả về lỗi nếu email hoặc mật khẩu sai
        }
    }
}
