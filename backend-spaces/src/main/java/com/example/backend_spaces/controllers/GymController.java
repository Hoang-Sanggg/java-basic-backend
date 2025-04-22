package com.example.backend_spaces.controllers;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend_spaces.model.GymPackage;
import com.example.backend_spaces.model.TrainingContract;
import com.example.backend_spaces.service.GymService;

@RestController
@RequestMapping("/api/gym")
public class GymController {

    @Autowired
    private GymService gymService;

    // Create - Tạo mới gói tập
    @PostMapping("/createPackage")
    public ResponseEntity<GymPackage> createGymPackage(@RequestParam String name, @RequestParam double price, @RequestParam int duration, @RequestParam String description, @RequestParam String gymId) {
        GymPackage gymPackage = gymService.createGymPackage(name, price, duration, description, gymId);
        return ResponseEntity.ok(gymPackage);
    }

    // Read - Lấy tất cả gói tập theo gymId
    @GetMapping("/packages/{gymId}")
    public ResponseEntity<List<GymPackage>> getGymPackagesByGymId(@PathVariable String gymId) {
        List<GymPackage> gymPackages = gymService.getGymPackagesByGymId(gymId);
        return ResponseEntity.ok(gymPackages);
    }

    // Read - Lấy gói tập theo ID
    @GetMapping("/package/{id}")
    public ResponseEntity<GymPackage> getGymPackageById(@PathVariable String id) {
        Optional<GymPackage> gymPackage = gymService.getGymPackageById(id);
        return gymPackage.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Update - Cập nhật gói tập
    @PutMapping("/updatePackage/{id}")
    public ResponseEntity<GymPackage> updateGymPackage(@PathVariable String id, @RequestParam String name, @RequestParam double price, @RequestParam int duration, @RequestParam String description, @RequestParam String gymId) {
        GymPackage updatedPackage = gymService.updateGymPackage(id, name, price, duration, description, gymId);
        return updatedPackage != null ? ResponseEntity.ok(updatedPackage) : ResponseEntity.notFound().build();
    }

    // Delete - Xóa gói tập
    @DeleteMapping("/deletePackage/{id}")
    public ResponseEntity<String> deleteGymPackage(@PathVariable String id) {
        return gymService.deleteGymPackage(id) ? ResponseEntity.ok("Gói tập đã được xóa") : ResponseEntity.notFound().build();
    }

    // API tạo hợp đồng tập luyện
    @PostMapping("/createContract")
    public ResponseEntity<TrainingContract> createTrainingContract(@RequestParam String memberId, @RequestParam String gymPackageId, @RequestParam java.util.Date startDate, @RequestParam java.util.Date endDate) {
        // Chuyển đổi từ java.util.Date thành java.sql.Date
        Date sqlStartDate = new Date(startDate.getTime());  // Chuyển đổi ngày bắt đầu
        Date sqlEndDate = new Date(endDate.getTime());  // Chuyển đổi ngày kết thúc

        // Tạo hợp đồng tập luyện
        TrainingContract contract = gymService.createTrainingContract(memberId, gymPackageId, sqlStartDate, sqlEndDate);
        return ResponseEntity.ok(contract);
    }

    // Read - Lấy tất cả hợp đồng tập luyện theo gymPackageId
    @GetMapping("/contracts/{gymPackageId}")
    public ResponseEntity<List<TrainingContract>> getTrainingContractsByGymPackageId(@PathVariable String gymPackageId) {
        List<TrainingContract> contracts = gymService.getTrainingContractsByGymPackageId(gymPackageId);
        return ResponseEntity.ok(contracts);
    }

    // Update - Cập nhật hợp đồng tập luyện
    @PutMapping("/updateContract/{id}")
    public ResponseEntity<TrainingContract> updateTrainingContract(@PathVariable String id, @RequestParam String memberId, @RequestParam String gymPackageId, @RequestParam Date startDate, @RequestParam Date endDate, @RequestParam boolean isActive) {
        TrainingContract updatedContract = gymService.updateTrainingContract(id, memberId, gymPackageId, startDate, endDate, isActive);
        return updatedContract != null ? ResponseEntity.ok(updatedContract) : ResponseEntity.notFound().build();
    }

    // Delete - Xóa hợp đồng tập luyện
    @DeleteMapping("/deleteContract/{id}")
    public ResponseEntity<String> deleteTrainingContract(@PathVariable String id) {
        return gymService.deleteTrainingContract(id) ? ResponseEntity.ok("Hợp đồng đã được xóa") : ResponseEntity.notFound().build();
    }
}
