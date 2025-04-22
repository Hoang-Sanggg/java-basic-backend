package com.example.backend_spaces.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend_spaces.model.GymPackage;
import com.example.backend_spaces.model.TrainingContract;
import com.example.backend_spaces.repository.GymPackageRepository;
import com.example.backend_spaces.repository.TrainingContractRepository;

@Service
public class GymService {

    @Autowired
    private GymPackageRepository gymPackageRepository;

    @Autowired
    private TrainingContractRepository trainingContractRepository;

    // ----------------------------- CRUD cho GymPackage -----------------------------

    // 1. Tạo gói tập
    public GymPackage createGymPackage(String name, double price, int duration, String description, String gymId) {
        GymPackage gymPackage = new GymPackage(name, price, duration, description, gymId);
        return gymPackageRepository.save(gymPackage);
    }

    // 2. Lấy tất cả gói tập theo gymId
    public List<GymPackage> getGymPackagesByGymId(String gymId) {
        return gymPackageRepository.findByGymId(gymId);  // Tìm tất cả gói tập theo gymId
    }

    // 3. Lấy gói tập theo ID
    public Optional<GymPackage> getGymPackageById(String id) {
        return gymPackageRepository.findById(id);
    }

    // 4. Cập nhật gói tập
    public GymPackage updateGymPackage(String id, String name, double price, int duration, String description, String gymId) {
        Optional<GymPackage> existingGymPackage = gymPackageRepository.findById(id);
        if (existingGymPackage.isPresent()) {
            GymPackage gymPackage = existingGymPackage.get();
            gymPackage.setName(name);
            gymPackage.setPrice(price);
            gymPackage.setDuration(duration);
            gymPackage.setDescription(description);
            gymPackage.setGymId(gymId);
            return gymPackageRepository.save(gymPackage);
        }
        return null;  // Trả về null nếu không tìm thấy gói tập
    }

    // 5. Xóa gói tập
    public boolean deleteGymPackage(String id) {
        if (gymPackageRepository.existsById(id)) {
            gymPackageRepository.deleteById(id);
            return true;
        }
        return false;  // Trả về false nếu không tìm thấy gói tập để xóa
    }

    // ------------------------- CRUD cho TrainingContract ---------------------------

    // 6. Tạo hợp đồng tập luyện
    public TrainingContract createTrainingContract(String memberId, String gymPackageId, String gymId, Date startDate, Date endDate) {
        TrainingContract contract = new TrainingContract(memberId, gymPackageId, gymId, startDate, endDate, true);
        return trainingContractRepository.save(contract);
    }

    // 8. Lấy hợp đồng tập luyện theo ID
    public Optional<TrainingContract> getTrainingContractById(String id) {
        return trainingContractRepository.findById(id);
    }

    public List<TrainingContract> getTrainingContractsByGymId(String gymId) {
        // Kiểm tra xem gymId có tồn tại trong cơ sở dữ liệu hay không
        if (gymId == null || gymId.trim().isEmpty()) {
            throw new IllegalArgumentException("GymId không hợp lệ");
        }

        List<TrainingContract> contracts = trainingContractRepository.findByGymId(gymId);

        // Kiểm tra nếu danh sách hợp đồng là null hoặc rỗng
        if (contracts == null || contracts.isEmpty()) {
            throw new RuntimeException("Không tìm thấy hợp đồng cho gymId: " + gymId);
        }

        return contracts;
    }

    // 10. Cập nhật hợp đồng tập luyện
    public TrainingContract updateTrainingContract(String id, String memberId, String gymPackageId, String gymId, Date startDate, Date endDate, boolean isActive) {
        Optional<TrainingContract> existingContract = trainingContractRepository.findById(id);
        if (existingContract.isPresent()) {
            TrainingContract contract = existingContract.get();
            contract.setMemberId(memberId);
            contract.setGymPackageId(gymPackageId);
            contract.setGymId(gymId);
            contract.setStartDate(startDate);
            contract.setEndDate(endDate);
            contract.setActive(isActive);
            return trainingContractRepository.save(contract);
        }
        return null;  // Trả về null nếu không tìm thấy hợp đồng
    }

    // 11. Xóa hợp đồng tập luyện
    public boolean deleteTrainingContract(String id) {
        if (trainingContractRepository.existsById(id)) {
            trainingContractRepository.deleteById(id);
            return true;
        }
        return false;  // Trả về false nếu không tìm thấy hợp đồng để xóa
    }
}
