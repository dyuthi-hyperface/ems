package com.example.employeemanagement.service;

import com.example.employeemanagement.entity.Project;

import java.util.List;

public interface ProjectService {

    Project createProject(Project project);

    List<Project> getAllProjects();

    Project getProjectById(Long id);

    Project updateProject(Long id, Project project);

    void deleteProject(Long id);
}
