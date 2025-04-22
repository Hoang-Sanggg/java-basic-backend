package com.example.backend_spaces.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class UserAccount {

    @Id
    private String id;
    private String email;
    private String password;  // Được mã hóa
    private String role;      // ADMIN, OWNER, v.v.
    private String gymId;

    // Constructors
    public UserAccount() {}

    public UserAccount(String email, String password, String role, String gymId) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.gymId = gymId;
    }

    // Getter & Setter
    public String getPassword() {
        return password;  // Trả về mật khẩu
    }

    public void setPassword(String password) {
        this.password = password;  // Set mật khẩu
    }

    // Các getter và setter khác
}
