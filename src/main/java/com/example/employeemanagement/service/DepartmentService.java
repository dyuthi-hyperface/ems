package com.example.employeemanagement.service;

import com.example.employeemanagement.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department createDepartment(Department department);

    List<Department> getAllDepartments();

    Department getDepartmentById(Long id);

    Department updateDepartment(Long id, Department department);

    void deleteDepartment(Long id);
}

