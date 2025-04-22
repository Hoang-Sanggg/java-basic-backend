package com.example.backend_spaces.model;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;  // Sử dụng java.sql.Date

@Document(collection = "training_contracts")
public class TrainingContract {

    @Id
    private String id;
    private String memberId;
    private String gymPackageId;
    private Date startDate;  // Dùng java.sql.Date
    private Date endDate;    // Dùng java.sql.Date
    private boolean isActive;

    // Constructors
    public TrainingContract() {}

    public TrainingContract(String memberId, String gymPackageId, Date startDate, Date endDate, boolean isActive) {
        this.memberId = memberId;
        this.gymPackageId = gymPackageId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getGymPackageId() {
        return gymPackageId;
    }

    public void setGymPackageId(String gymPackageId) {
        this.gymPackageId = gymPackageId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
