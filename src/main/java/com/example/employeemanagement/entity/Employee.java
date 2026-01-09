package com.example.employeemanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(
        name = "employees",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username")
        }
)
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee name must not be blank")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "Username must not be blank")
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Password must not be blank")
    @Column(nullable = false)
    private String password;

    // ---- Relationships ----

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    // ---- Getters & Setters ----

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Department getDepartment() {
        return department;
    }

    public Project getProject() {
        return project;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
