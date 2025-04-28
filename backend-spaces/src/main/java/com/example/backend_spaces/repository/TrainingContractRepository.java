package com.example.backend_spaces.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.backend_spaces.model.TrainingContract;

public interface TrainingContractRepository extends MongoRepository<TrainingContract, String> {
    List<TrainingContract> findByGymId(String gymId);
}
