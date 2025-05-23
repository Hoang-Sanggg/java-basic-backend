package com.example.backend_spaces.security;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JwtTokenUtil {

    private static final String SECRET_KEY = "mySuperSecureKey";  // Thay thế bằng một key mạnh mẽ hơn

    // Tạo JWT token, bao gồm cả gymId
    public static String generateToken(String email, String gymId) {
        return JWT.create()
                .withSubject(email)  // Đặt email làm subject
                .withClaim("gymId", gymId)  // Thêm gymId vào claim
                .withIssuedAt(new Date())  // Thời gian tạo token
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))  // Token hết hạn sau 1 ngày
                .sign(Algorithm.HMAC256(SECRET_KEY));  // Mã hóa với thuật toán HMAC256
    }

    // Giải mã và lấy thông tin từ token
    public static String extractEmail(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token)
                .getSubject();  // Lấy subject (email)
    }

    // Lấy gymId từ token
    public static String extractGymId(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET_KEY))
                .build()
                .verify(token)
                .getClaim("gymId").asString();  // Lấy gymId từ claim trong token
    }
}
