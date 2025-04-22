package com.example.backend_spaces.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.backend_spaces.model.TrainingContract;

public interface TrainingContractRepository extends MongoRepository<TrainingContract, String> {
    // Có thể thêm các phương thức tùy chỉnh nếu cần
    List<TrainingContract> findByGymPackageId(String gymPackageId);
}
