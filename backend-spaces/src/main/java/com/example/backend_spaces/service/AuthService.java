package com.example.backend_spaces.service;

import java.util.Optional;

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
    public String register(String email, String rawPassword, String role, String gymId) {
        // Kiểm tra nếu email đã tồn tại
        if (userRepo.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email đã tồn tại");
        }

        // Mã hóa mật khẩu
        String hashedPassword = BCrypt.hashpw(rawPassword, BCrypt.gensalt());

        // Tạo tài khoản mới
        UserAccount user = new UserAccount(email, hashedPassword, role, gymId);

        // Lưu vào cơ sở dữ liệu
        userRepo.save(user);

        return "Đăng ký thành công";
    }

    // Đăng nhập (trả về token nếu thành công)
    public Optional<String> login(String email, String rawPassword) {
        Optional<UserAccount> userOpt = userRepo.findByEmail(email);
        if (userOpt.isPresent()) {
            UserAccount user = userOpt.get();
            if (BCrypt.checkpw(rawPassword, user.getPassword())) {
                // Tạo JWT token khi đăng nhập thành công
                String token = JwtTokenUtil.generateToken(user.getGymId());
                return Optional.of(token);
            }
        }
        return Optional.empty();  // Nếu không tìm thấy user hoặc mật khẩu sai
    }
}
