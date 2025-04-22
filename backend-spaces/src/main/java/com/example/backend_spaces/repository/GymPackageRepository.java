package com.example.backend_spaces.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.backend_spaces.model.GymPackage;

public interface GymPackageRepository extends MongoRepository<GymPackage, String> {
    List<GymPackage> findByGymId(String gymId);  // Tìm tất cả gói tập theo gymId
}
