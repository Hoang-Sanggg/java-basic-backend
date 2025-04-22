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
    public String getEmail() {
        return email;  // Phương thức getter cho email
    }

    public void setEmail(String email) {
        this.email = email;  // Phương thức setter cho email
    }

    public String getPassword() {
        return password;  // Phương thức getter cho password
    }

    public void setPassword(String password) {
        this.password = password;  // Phương thức setter cho password
    }

    public String getRole() {
        return role;  // Phương thức getter cho role
    }

    public void setRole(String role) {
        this.role = role;  // Phương thức setter cho role
    }

    public String getGymId() {
        return gymId;  // Phương thức getter cho gymId
    }

    public void setGymId(String gymId) {
        this.gymId = gymId;  // Phương thức setter cho gymId
    }
}
