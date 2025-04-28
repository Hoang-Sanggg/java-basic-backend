package com.example.backend_spaces.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_spaces.model.GymPackage;
import com.example.backend_spaces.model.TrainingContract;
import com.example.backend_spaces.security.JwtTokenUtil;
import com.example.backend_spaces.service.GymService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/gym")
public class GymController {

    @Autowired
    private GymService gymService;

    // ------------------------ Helper ------------------------

    private String getGymIdFromToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            return JwtTokenUtil.extractGymId(token);
        }
        return null;
    }

    // ------------------------ API cho GymPackage ------------------------

    @PostMapping("/createPackage")
    public ResponseEntity<GymPackage> createGymPackage(@RequestBody GymPackage gymPackageRequest) {
        if (gymPackageRequest.getName() == null || gymPackageRequest.getPrice() <= 0 || gymPackageRequest.getDuration() <= 0
                || gymPackageRequest.getDescription() == null || gymPackageRequest.getGymId() == null) {
            return ResponseEntity.badRequest().build();
        }
        GymPackage gymPackage = gymService.createGymPackage(
                gymPackageRequest.getName(),
                gymPackageRequest.getPrice(),
                gymPackageRequest.getDuration(),
                gymPackageRequest.getDescription(),
                gymPackageRequest.getGymId());
        return ResponseEntity.ok(gymPackage);
    }

    @GetMapping("/packages/{gymId}")
    public ResponseEntity<List<GymPackage>> getGymPackagesByGymId(@PathVariable String gymId) {
        List<GymPackage> gymPackages = gymService.getGymPackagesByGymId(gymId);
        return ResponseEntity.ok(gymPackages);
    }

    @GetMapping("/package/{id}")
    public ResponseEntity<GymPackage> getGymPackageById(@PathVariable String id) {
        return gymService.getGymPackageById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/updatePackage/{id}")
    public ResponseEntity<GymPackage> updateGymPackage(@PathVariable String id, @RequestBody GymPackage gymPackageRequest) {
        GymPackage updated = gymService.updateGymPackage(
                id,
                gymPackageRequest.getName(),
                gymPackageRequest.getPrice(),
                gymPackageRequest.getDuration(),
                gymPackageRequest.getDescription(),
                gymPackageRequest.getGymId());
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deletePackage/{id}")
    public ResponseEntity<String> deleteGymPackage(@PathVariable String id) {
        boolean deleted = gymService.deleteGymPackage(id);
        return deleted ? ResponseEntity.ok("Gói tập đã được xóa") : ResponseEntity.notFound().build();
    }

    // ------------------------ API cho TrainingContract ------------------------

    @PostMapping("/createContract")
    public ResponseEntity<TrainingContract> createTrainingContract(@RequestBody TrainingContract contractRequest) {
        if (contractRequest.getMemberId() == null || contractRequest.getGymPackageId() == null
                || contractRequest.getStartDate() == null || contractRequest.getEndDate() == null) {
            return ResponseEntity.badRequest().build();
        }

        TrainingContract contract = gymService.createTrainingContract(
                contractRequest.getMemberId(),
                contractRequest.getGymPackageId(),
                contractRequest.getGymId(),
                contractRequest.getStartDate(),
                contractRequest.getEndDate());

        return ResponseEntity.ok(contract);
    }

    @GetMapping("/contractsByGym/{gymId}")
    public ResponseEntity<List<TrainingContract>> getTrainingContractsByGymId(@PathVariable String gymId) {
        List<TrainingContract> contracts = gymService.getTrainingContractsByGymId(gymId);
        return ResponseEntity.ok(contracts);
    }

    @GetMapping("/contract/{id}")
    public ResponseEntity<TrainingContract> getTrainingContractById(@PathVariable String id) {
        return gymService.getTrainingContractById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/updateContract/{id}")
    public ResponseEntity<TrainingContract> updateTrainingContract(@PathVariable String id, @RequestBody TrainingContract contractRequest) {
        TrainingContract updated = gymService.updateTrainingContract(
                id,
                contractRequest.getMemberId(),
                contractRequest.getGymPackageId(),
                contractRequest.getGymId(),
                contractRequest.getStartDate(),
                contractRequest.getEndDate(),
                contractRequest.isActive());

        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deleteContract/{id}")
    public ResponseEntity<String> deleteTrainingContract(@PathVariable String id) {
        boolean deleted = gymService.deleteTrainingContract(id);
        return deleted ? ResponseEntity.ok("Hợp đồng đã được xóa") : ResponseEntity.notFound().build();
    }
}
