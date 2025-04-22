package com.example.backend_spaces.service;

import com.example.backend_spaces.model.UserAccount;
import com.example.backend_spaces.repository.UserAccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

@Service
public class AuthService {

    @Autowired
    private UserAccountRepository userRepo;

    public Optional<UserAccount> login(String email, String rawPassword) {
        Optional<UserAccount> userOpt = userRepo.findByEmail(email);
        if (userOpt.isPresent()) {
            UserAccount user = userOpt.get();
            if (BCrypt.checkpw(rawPassword, user.getPassword())) {
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

    // Optional: method để mã hóa password khi tạo tài khoản
    public String hashPassword(String raw) {
        return BCrypt.hashpw(raw, BCrypt.gensalt());
    }
}
