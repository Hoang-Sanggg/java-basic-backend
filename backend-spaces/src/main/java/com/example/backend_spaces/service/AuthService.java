package com.example.backend_spaces.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend_spaces.model.UserAccount;
import com.example.backend_spaces.repository.UserAccountRepository;
import com.example.backend_spaces.security.JwtTokenUtil;

@Service
public class AuthService {

    @Autowired
    private UserAccountRepository userRepo;

    // Đăng ký tài khoản mới
    public void register(String email, String rawPassword, String role, String gymId) {
        // Kiểm tra email có tồn tại trong hệ thống không
        if (userRepo.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email đã tồn tại");
        }

        // Mã hóa mật khẩu
        String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());

        // Tạo tài khoản mới
        UserAccount user = new UserAccount(email, hashedPassword, role, gymId);
        userRepo.save(user);
    }

    // Đăng nhập và trả về token nếu thông tin hợp lệ
    public String login(String email, String rawPassword) {
        UserAccount user = userRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("Email không tồn tại"));

        // Kiểm tra mật khẩu
        if (BCrypt.checkpw(rawPassword, user.getPassword())) {
            // Lấy gymId từ UserAccount
            String gymId = user.getGymId();

            // Tạo JWT token với email và gymId
            String token = JwtTokenUtil.generateToken(user.getEmail(), gymId);

            // Trả về token với định dạng Bearer token
            return token;
        }

        throw new RuntimeException("Mật khẩu sai");
    }
}
