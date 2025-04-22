package com.example.backend_spaces.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend_spaces.model.GymPackage;
import com.example.backend_spaces.model.TrainingContract;  // Import java.sql.Date
import com.example.backend_spaces.repository.GymPackageRepository;
import com.example.backend_spaces.repository.TrainingContractRepository;

@Service
public class GymService {

    @Autowired
    private GymPackageRepository gymPackageRepository;

    @Autowired
    private TrainingContractRepository trainingContractRepository;

    // Create - Tạo mới gói tập
    public GymPackage createGymPackage(String name, double price, int duration, String description, String gymId) {
        GymPackage gymPackage = new GymPackage(name, price, duration, description, gymId);
        return gymPackageRepository.save(gymPackage);
    }

    // Read - Lấy tất cả gói tập theo gymId
    public List<GymPackage> getGymPackagesByGymId(String gymId) {
        return gymPackageRepository.findByGymId(gymId);  // Lấy tất cả gói tập theo gymId
    }

    // Read - Lấy gói tập theo ID
    public Optional<GymPackage> getGymPackageById(String id) {
        return gymPackageRepository.findById(id);
    }

    // Update - Cập nhật gói tập
    public GymPackage updateGymPackage(String id, String name, double price, int duration, String description, String gymId) {
        Optional<GymPackage> gymPackageOptional = gymPackageRepository.findById(id);
        if (gymPackageOptional.isPresent()) {
            GymPackage gymPackage = gymPackageOptional.get();
            gymPackage.setName(name);
            gymPackage.setPrice(price);
            gymPackage.setDuration(duration);
            gymPackage.setDescription(description);
            gymPackage.setGymId(gymId);
            return gymPackageRepository.save(gymPackage);
        }
        return null;  // Trả về null nếu không tìm thấy gói tập
    }

    // Delete - Xóa gói tập
    public boolean deleteGymPackage(String id) {
        if (gymPackageRepository.existsById(id)) {
            gymPackageRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Create - Tạo hợp đồng tập luyện
    public TrainingContract createTrainingContract(String memberId, String gymPackageId, Date startDate, Date endDate) {
        TrainingContract contract = new TrainingContract(memberId, gymPackageId, startDate, endDate, true);
        return trainingContractRepository.save(contract);
    }

    // Read - Lấy tất cả hợp đồng tập luyện theo gymPackageId
    public List<TrainingContract> getTrainingContractsByGymPackageId(String gymPackageId) {
        return trainingContractRepository.findByGymPackageId(gymPackageId);
    }

    // Update - Cập nhật hợp đồng tập luyện
    public TrainingContract updateTrainingContract(String id, String memberId, String gymPackageId, Date startDate, Date endDate, boolean isActive) {
        Optional<TrainingContract> contractOptional = trainingContractRepository.findById(id);
        if (contractOptional.isPresent()) {
            TrainingContract contract = contractOptional.get();
            contract.setMemberId(memberId);
            contract.setGymPackageId(gymPackageId);
            contract.setStartDate(startDate);
            contract.setEndDate(endDate);
            contract.setActive(isActive);
            return trainingContractRepository.save(contract);
        }
        return null;  // Trả về null nếu không tìm thấy hợp đồng
    }

    // Delete - Xóa hợp đồng tập luyện
    public boolean deleteTrainingContract(String id) {
        if (trainingContractRepository.existsById(id)) {
            trainingContractRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
