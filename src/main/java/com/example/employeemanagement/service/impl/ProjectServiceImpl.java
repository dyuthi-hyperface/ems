package com.example.employeemanagement.service.impl;

import com.example.employeemanagement.entity.Project;
import com.example.employeemanagement.repository.ProjectRepository;
import com.example.employeemanagement.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Project not found with id: " + id)
                );
    }

    @Override
    public Project updateProject(Long id, Project project) {
        Project existing = getProjectById(id);

        existing.setName(project.getName());
        existing.setDepartment(project.getDepartment());

        return projectRepository.save(existing);
    }

    @Override
    public void deleteProject(Long id) {
        Project existing = getProjectById(id);
        projectRepository.delete(existing);
    }
}
