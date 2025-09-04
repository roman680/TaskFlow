package org.example.taskflow.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.taskflow.model.Project;
import org.example.taskflow.repository.ProjectRepository;
import org.example.taskflow.service.ProjectService;

import java.util.List;

public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public Project create(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project getById(Long id) {
        return projectRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Project not found with id: " + id));
    }

    @Override
    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project update(Long id, Project updated) {
        Project projectFromDb = getById(id);

        if (updated.getName() != null) {
            projectFromDb.setName(updated.getName());
        }
        if (updated.getDescription() != null) {
            projectFromDb.setDescription(updated.getDescription());
        }
        if (updated.getStartDate() != null) {
            projectFromDb.setStartDate(updated.getStartDate());
        }
        if (updated.getEndDate() != null) {
            projectFromDb.setEndDate(updated.getEndDate());
        }
        if (updated.getStatus() != null) {
            projectFromDb.setStatus(updated.getStatus());
        }

        return projectRepository.save(projectFromDb);
    }

    @Override
    public void delete(Long id) {
        if (!projectRepository.existsById(id)) {
            throw new EntityNotFoundException("Project not found: " + id);
        }
        projectRepository.deleteById(id);
    }
}
