package com.example.backend_spaces.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
public class Employee {

    @Id
    private String id;  // MongoDB sử dụng ObjectId

    private String fullName;  // Họ và tên
    private String position;  // Vị trí công việc (e.g., Trainer, Receptionist)
    private String phone;     // Số điện thoại
    private String email;     // Địa chỉ email

    // Mối quan hệ với Gym
    private String gymId;     // ID của phòng gym mà nhân viên làm việc

    // Constructors
    public Employee() {}

    public Employee(String fullName, String position, String phone, String email, String gymId) {
        this.fullName = fullName;
        this.position = position;
        this.phone = phone;
        this.email = email;
        this.gymId = gymId;
    }

    // Getters và Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGymId() {
        return gymId;
    }

    public void setGymId(String gymId) {
        this.gymId = gymId;
    }
}
