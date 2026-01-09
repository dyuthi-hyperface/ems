package com.example.employeemanagement.service;

import com.example.employeemanagement.entity.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository,
                               PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        // 🔐 HASH PASSWORD BEFORE SAVING
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    @Override
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existing = getEmployeeById(id);
        existing.setUsername(employee.getUsername());

        if (employee.getPassword() != null) {
            existing.setPassword(passwordEncoder.encode(employee.getPassword()));
        }

        existing.setDepartment(employee.getDepartment());
        existing.setProject(employee.getProject());

        return employeeRepository.save(existing);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
