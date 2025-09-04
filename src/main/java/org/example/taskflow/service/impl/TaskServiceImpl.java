package org.example.taskflow.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.taskflow.model.Attachment;
import org.example.taskflow.model.Comment;
import org.example.taskflow.model.Label;
import org.example.taskflow.model.Task;
import org.example.taskflow.repository.AttachmentRepository;
import org.example.taskflow.repository.CommentRepository;
import org.example.taskflow.repository.LabelRepository;
import org.example.taskflow.repository.ProjectRepository;
import org.example.taskflow.repository.TaskRepository;
import org.example.taskflow.repository.UserRepository;
import org.example.taskflow.service.TaskService;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final LabelRepository labelRepository;
    private final AttachmentRepository attachmentRepository;
    private final CommentRepository commentRepository;

    public TaskServiceImpl(TaskRepository taskRepository,
                           ProjectRepository projectRepository,
                           UserRepository userRepository,
                           LabelRepository labelRepository,
                           AttachmentRepository attachmentRepository,
                           CommentRepository commentRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
        this.labelRepository = labelRepository;
        this.attachmentRepository = attachmentRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task update(Long id, Task updated) {
        Task taskFromDb = getById(id);

        if (updated.getName() != null) {
            taskFromDb.setName(updated.getName());
        }
        if (updated.getDescription() != null) {
            taskFromDb.setDescription(updated.getDescription());
        }
        if (updated.getPriority() != null) {
            taskFromDb.setPriority(updated.getPriority());
        }
        if (updated.getStatus() != null) {
            taskFromDb.setStatus(updated.getStatus());
        }
        if (updated.getDueDate() != null) {
            taskFromDb.setDueDate(updated.getDueDate());
        }
        if (updated.getProject() != null) {
            taskFromDb.setProject(updated.getProject());
        }
        if (updated.getAssignee() != null) {
            taskFromDb.setAssignee(updated.getAssignee());
        }

        return taskRepository.save(taskFromDb);
    }

    @Override
    public void delete(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new EntityNotFoundException("Task not found with id: " + id);
        }
        taskRepository.deleteById(id);
    }

    @Override
    public Task changeStatus(Long taskId, Task.Status status) {
        Task task = getById(taskId);
        task.setStatus(status);
        return taskRepository.save(task);
    }

    @Override
    public Task changePriority(Long taskId, Task.Priority priority) {
        Task task = getById(taskId);
        task.setPriority(priority);
        return taskRepository.save(task);
    }

    @Override
    public Task assignToUser(Long taskId, Long userId) {
        Task task = getById(taskId);
        var user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User not found with id: " + userId));
        task.setAssignee(user);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> findByProject(Long projectId) {
        projectRepository.findById(projectId).orElseThrow(() ->
                new RuntimeException("Project not found with id: " + projectId));
        return taskRepository.findByProjectId(projectId);
    }

    @Override
    public List<Task> findByAssignee(Long userId) {
        userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("User not found with id: " + userId));
        return taskRepository.findByAssigneeId(userId);
    }

    @Override
    public Task addLabel(Long taskId, Long labelId) {
        Task task = getById(taskId);
        Label label = labelRepository.findById(labelId).orElseThrow(() ->
                new RuntimeException("Label not found with id: " + labelId));
        task.getLabels().add(label);
        return taskRepository.save(task);
    }

    @Override
    public Task removeLabel(Long taskId, Long labelId) {
        Task task = getById(taskId);
        task.getLabels().removeIf(l -> l.getId().equals(labelId));
        return taskRepository.save(task);
    }

    @Override
    public Task addAttachment(Long taskId, String filename, String dropboxFileId) {
        Task task = getById(taskId);
        Attachment attachment = new Attachment();
        attachment.setFilename(filename);
        attachment.setDropboxFileId(dropboxFileId);
        attachment.setTask(task);
        attachmentRepository.save(attachment);
        task.getAttachments().add(attachment);
        return taskRepository.save(task);
    }

    @Override
    public Task removeAttachment(Long taskId, Long attachmentId) {
        Task task = getById(taskId);
        task.getAttachments().removeIf(a -> a.getId().equals(attachmentId));
        return taskRepository.save(task);
    }

    @Override
    public List<Comment> getComments(Long taskId) {
        getById(taskId);
        return commentRepository.findByTaskId(taskId);
    }
}
