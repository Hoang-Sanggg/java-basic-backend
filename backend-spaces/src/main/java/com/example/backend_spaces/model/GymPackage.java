package com.example.backend_spaces.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "gym_packages")
public class GymPackage {

    @Id
    private String id;  // ID của gói tập
    private String name;  // Tên gói tập (ví dụ: "Gói 1 tháng", "Gói VIP")
    private double price;  // Giá của gói tập
    private int duration;  // Thời gian hiệu lực (ví dụ: 30 ngày)
    private String description;  // Mô tả gói tập
    private String gymId;  // ID của phòng gym liên kết với gói tập

    // Constructors
    public GymPackage() {}

    public GymPackage(String name, double price, int duration, String description, String gymId) {
        this.name = name;
        this.price = price;
        this.duration = duration;
        this.description = description;
        this.gymId = gymId;
    }

    // Getters & Setters
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGymId() {
        return gymId;
    }

    public void setGymId(String gymId) {
        this.gymId = gymId;
    }
}
