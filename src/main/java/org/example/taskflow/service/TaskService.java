package org.example.taskflow.service;

import org.example.taskflow.model.*;
import java.util.List;

public interface TaskService {
    Task create(Task task);
    Task getById(Long id);
    List<Task> getAll();
    Task update(Long id, Task updated);
    void delete(Long id);

    Task changeStatus(Long taskId, Task.Status status);
    Task changePriority(Long taskId, Task.Priority priority);
    Task assignToUser(Long taskId, Long userId);
    List<Task> findByProject(Long projectId);
    List<Task> findByAssignee(Long userId);

    Task addLabel(Long taskId, Long labelId);
    Task removeLabel(Long taskId, Long labelId);

    Task addAttachment(Long taskId, String filename, String dropboxFileId);
    Task removeAttachment(Long taskId, Long attachmentId);

    List<Comment> getComments(Long taskId);
}
