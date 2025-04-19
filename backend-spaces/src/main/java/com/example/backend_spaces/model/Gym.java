package com.example.backend_spaces.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gyms")
public class Gym {

    @Id
    private String id;  // MongoDB sử dụng ObjectId làm kiểu dữ liệu cho ID

    private String name;
    private String address;
    private Integer capacity;  // Sức chứa của phòng gym
    private String openTime;  // Thời gian mở cửa
    private String closeTime;  // Thời gian đóng cửa

    // Constructors
    public Gym() {}

    public Gym(String name, String address, Integer capacity, String openTime, String closeTime) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    // Getters và Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }
}
