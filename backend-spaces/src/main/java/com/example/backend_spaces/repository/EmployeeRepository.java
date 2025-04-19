package com.example.backend_spaces.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.backend_spaces.model.Employee;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
    List<Employee> findByGymId(String gymId);
}
