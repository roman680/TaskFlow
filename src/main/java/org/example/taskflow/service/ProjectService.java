package org.example.taskflow.service;

import org.example.taskflow.model.Project;
import java.util.List;

public interface ProjectService {
    Project create(Project project);
    Project getById(Long id);
    List<Project> getAll();
    Project update(Long id, Project updated);
    void delete(Long id);
}
