package com.example.backend_spaces.controllers;

import java.sql.Date;
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

    // Lấy gymId từ token
    private String getGymIdFromToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Loại bỏ "Bearer " khỏi token
            return JwtTokenUtil.extractGymId(token);  // Trích xuất gymId từ token
        }
        return null;
    }

    // ------------------------ API cho GymPackage ------------------------

    // 1. Tạo gói tập
    @PostMapping("/createPackage")
    public ResponseEntity<GymPackage> createGymPackage(@RequestBody GymPackage gymPackageRequest) {
        // Kiểm tra tham số bắt buộc
        if (gymPackageRequest.getName() == null || gymPackageRequest.getPrice() <= 0 || gymPackageRequest.getDuration() <= 0 || gymPackageRequest.getDescription() == null || gymPackageRequest.getGymId() == null) {
            return ResponseEntity.status(400).body(null);  // Thiếu tham số bắt buộc
        }

        // Tạo gói tập mới
        GymPackage gymPackage = gymService.createGymPackage(gymPackageRequest.getName(), gymPackageRequest.getPrice(), gymPackageRequest.getDuration(), gymPackageRequest.getDescription(), gymPackageRequest.getGymId());
        return ResponseEntity.ok(gymPackage);  // Trả về gói tập mới
    }

    // 2. Lấy tất cả gói tập theo gymId
    @GetMapping("/packages/{gymId}")
    public ResponseEntity<List<GymPackage>> getGymPackagesByGymId(@PathVariable String gymId) {
        List<GymPackage> gymPackages = gymService.getGymPackagesByGymId(gymId);
        return ResponseEntity.ok(gymPackages);
    }

    // 3. Lấy gói tập theo ID
    @GetMapping("/package/{id}")
    public ResponseEntity<GymPackage> getGymPackageById(@PathVariable String id) {
        return gymService.getGymPackageById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));  // Trả về 404 nếu không tìm thấy gói tập
    }

    // 4. Cập nhật gói tập
    @PutMapping("/updatePackage/{id}")
    public ResponseEntity<GymPackage> updateGymPackage(@PathVariable String id, @RequestBody GymPackage gymPackageRequest) {
        GymPackage updatedGymPackage = gymService.updateGymPackage(id, gymPackageRequest.getName(), gymPackageRequest.getPrice(), gymPackageRequest.getDuration(), gymPackageRequest.getDescription(), gymPackageRequest.getGymId());
        return updatedGymPackage != null ? ResponseEntity.ok(updatedGymPackage) : ResponseEntity.status(404).body(null);
    }

    // 5. Xóa gói tập
    @DeleteMapping("/deletePackage/{id}")
    public ResponseEntity<String> deleteGymPackage(@PathVariable String id) {
        boolean deleted = gymService.deleteGymPackage(id);
        return deleted ? ResponseEntity.ok("Gói tập đã được xóa") : ResponseEntity.status(404).body("Gói tập không tìm thấy");
    }

    // ---------------------- API cho TrainingContract ----------------------

    // 6. Tạo hợp đồng tập luyện
    @PostMapping("/createContract")
    public ResponseEntity<TrainingContract> createTrainingContract(@RequestBody TrainingContract contractRequest) {
        // Kiểm tra tham số bắt buộc
        if (contractRequest.getMemberId() == null || contractRequest.getGymPackageId() == null || contractRequest.getStartDate() == null || contractRequest.getEndDate() == null) {
            return ResponseEntity.status(400).body(null);  // Thiếu tham số bắt buộc
        }

        // Chuyển đổi từ java.util.Date thành java.sql.Date
        Date sqlStartDate = new Date(contractRequest.getStartDate().getTime());
        Date sqlEndDate = new Date(contractRequest.getEndDate().getTime());

        // Tạo hợp đồng tập luyện
        TrainingContract contract = gymService.createTrainingContract(contractRequest.getMemberId(), contractRequest.getGymPackageId(), contractRequest.getGymId(), sqlStartDate, sqlEndDate);
        return ResponseEntity.ok(contract);
    }

    // 9. Lấy tất cả hợp đồng theo gymId
    @GetMapping("/contractsByGym/{gymId}")
    public ResponseEntity<List<TrainingContract>> getTrainingContractsByGymId(@PathVariable String gymId) {
        try {
            List<TrainingContract> contracts = gymService.getTrainingContractsByGymId(gymId);
            if (contracts.isEmpty()) {
                return ResponseEntity.status(404).body(null);  // Nếu không tìm thấy hợp đồng
            }
            return ResponseEntity.ok(contracts);  // Trả về danh sách hợp đồng nếu có
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);  // Trả về 500 nếu có lỗi xảy ra
        }
    }

    // 9. Lấy hợp đồng tập luyện theo ID
    @GetMapping("/contract/{id}")
    public ResponseEntity<TrainingContract> getTrainingContractById(@PathVariable String id) {
        return gymService.getTrainingContractById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body(null));  // Trả về 404 nếu không tìm thấy hợp đồng
    }

    // 10. Cập nhật hợp đồng tập luyện
    @PutMapping("/updateContract/{id}")
    public ResponseEntity<TrainingContract> updateTrainingContract(@PathVariable String id, @RequestBody TrainingContract contractRequest) {
        TrainingContract updatedContract = gymService.updateTrainingContract(id, contractRequest.getMemberId(), contractRequest.getGymPackageId(), contractRequest.getGymId(), contractRequest.getStartDate(), contractRequest.getEndDate(), contractRequest.isActive());
        return updatedContract != null ? ResponseEntity.ok(updatedContract) : ResponseEntity.status(404).body(null);
    }

    // 11. Xóa hợp đồng tập luyện
    @DeleteMapping("/deleteContract/{id}")
    public ResponseEntity<String> deleteTrainingContract(@PathVariable String id) {
        boolean deleted = gymService.deleteTrainingContract(id);
        return deleted ? ResponseEntity.ok("Hợp đồng đã được xóa") : ResponseEntity.status(404).body("Hợp đồng không tìm thấy");
    }
}
