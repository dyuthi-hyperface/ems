package com.example.employeemanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ProjectRequest {

    @NotBlank(message = "Project name must not be blank")
    @Size(min = 2, max = 50, message = "Project name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Department id must not be null")
    private Long departmentId;

    public String getName() {
        return name;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
