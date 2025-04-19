package com.example.backend_spaces.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "members")
public class Member {

    @Id
    private String id;  // MongoDB sử dụng ObjectId

    private String fullName;  // Họ và tên
    private Integer age;      // Tuổi
    private String phone;     // Số điện thoại
    private String email;     // Địa chỉ email
    private String membershipType;  // Loại hội viên (e.g., Gold, Silver, Bronze)

    // Mối quan hệ với Gym
    private String gymId;     // ID của phòng gym mà hội viên tham gia

    // Constructors
    public Member() {}

    public Member(String fullName, Integer age, String phone, String email, String membershipType, String gymId) {
        this.fullName = fullName;
        this.age = age;
        this.phone = phone;
        this.email = email;
        this.membershipType = membershipType;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public String getGymId() {
        return gymId;
    }

    public void setGymId(String gymId) {
        this.gymId = gymId;
    }
}

