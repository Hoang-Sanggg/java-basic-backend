package com.example.backend_spaces.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend_spaces.model.Employee;
import com.example.backend_spaces.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Lấy danh sách hội viên theo gymId
    public List<Employee> getEmployeesByGym(String gymId) {
        return employeeRepository.findByGymId(gymId);
    }

    // Tạo mới một hội viên
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    // Cập nhật hội viên
    public Employee updateEmployee(String id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        existingEmployee.setFullName(employee.getFullName());
        existingEmployee.setPhone(employee.getPhone());
        existingEmployee.setEmail(employee.getEmail());
        return employeeRepository.save(existingEmployee);
    }

    // Xóa hội viên theo ID
    public void deleteEmployee(String id) {
        employeeRepository.deleteById(id);
    }
}
