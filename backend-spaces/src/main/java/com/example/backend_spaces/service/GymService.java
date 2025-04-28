package com.example.backend_spaces.service;

import java.util.Date;
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

    // ------------------------ CRUD cho GymPackage ------------------------

    public GymPackage createGymPackage(String name, double price, int duration, String description, String gymId) {
        GymPackage gymPackage = new GymPackage(name, price, duration, description, gymId);
        return gymPackageRepository.save(gymPackage);
    }

    public List<GymPackage> getGymPackagesByGymId(String gymId) {
        return gymPackageRepository.findByGymId(gymId);
    }

    public Optional<GymPackage> getGymPackageById(String id) {
        return gymPackageRepository.findById(id);
    }

    public GymPackage updateGymPackage(String id, String name, double price, int duration, String description, String gymId) {
        Optional<GymPackage> existingPackage = gymPackageRepository.findById(id);
        if (existingPackage.isPresent()) {
            GymPackage gymPackage = existingPackage.get();
            gymPackage.setName(name);
            gymPackage.setPrice(price);
            gymPackage.setDuration(duration);
            gymPackage.setDescription(description);
            gymPackage.setGymId(gymId);
            return gymPackageRepository.save(gymPackage);
        }
        return null;
    }

    public boolean deleteGymPackage(String id) {
        if (gymPackageRepository.existsById(id)) {
            gymPackageRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ------------------------ CRUD cho TrainingContract ------------------------

    public TrainingContract createTrainingContract(String memberId, String gymPackageId, String gymId, Date startDate, Date endDate) {
        TrainingContract contract = new TrainingContract(memberId, gymPackageId, gymId, startDate, endDate, true);
        return trainingContractRepository.save(contract);
    }

    public List<TrainingContract> getTrainingContractsByGymId(String gymId) {
        if (gymId == null || gymId.trim().isEmpty()) {
            throw new IllegalArgumentException("GymId không hợp lệ");
        }
        return trainingContractRepository.findByGymId(gymId);
    }

    public Optional<TrainingContract> getTrainingContractById(String id) {
        return trainingContractRepository.findById(id);
    }

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
        return null;
    }

    public boolean deleteTrainingContract(String id) {
        if (trainingContractRepository.existsById(id)) {
            trainingContractRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
