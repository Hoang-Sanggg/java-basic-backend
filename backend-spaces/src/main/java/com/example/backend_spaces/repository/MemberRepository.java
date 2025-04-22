package com.example.backend_spaces.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.backend_spaces.model.Member;

public interface MemberRepository extends MongoRepository<Member, String> {
    List<Member> findByGymId(String gymId);
    // Trong MemberRepository.java
    Optional<Member> findByEmailAndPhone(String email, String phone);

}

