package com.example.employeemanagement.service.impl;

import com.example.employeemanagement.entity.Department;
import com.example.employeemanagement.repository.DepartmentRepository;
import com.example.employeemanagement.service.DepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Department not found with id: " + id)
                );
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        Department existing = getDepartmentById(id);

        existing.setName(department.getName());

        return departmentRepository.save(existing);
    }

    @Override
    public void deleteDepartment(Long id) {
        Department existing = getDepartmentById(id);
        departmentRepository.delete(existing);
    }
}
