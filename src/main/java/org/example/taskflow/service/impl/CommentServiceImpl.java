package org.example.taskflow.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.taskflow.model.Comment;
import org.example.taskflow.model.CommentId;
import org.example.taskflow.repository.CommentRepository;
import org.example.taskflow.service.CommentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment create(Comment comment) {
        if (comment.getId() == null) {
            if (comment.getUser() == null || comment.getUser().getId() == null) {
                throw new IllegalArgumentException("User (with id) is required to create a comment.");
            }
            if (comment.getTask() == null || comment.getTask().getId() == null) {
                throw new IllegalArgumentException("Task (with id) is required to create a comment.");
            }
            comment.setId(new CommentId(
                    comment.getUser().getId(),
                    comment.getTask().getId(),
                    LocalDateTime.now()
            ));
        } else {
            if (comment.getUser() != null && comment.getUser().getId() != null) {
                comment.getId().setUserId(comment.getUser().getId());
            }
            if (comment.getTask() != null && comment.getTask().getId() != null) {
                comment.getId().setTaskId(comment.getTask().getId());
            }
            if (comment.getId().getTimeStamp() == null) {
                comment.getId().setTimeStamp(LocalDateTime.now());
            }
        }
        return commentRepository.save(comment);
    }

    @Override
    public Comment getById(CommentId id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + id));
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment update(CommentId id, Comment updated) {
        Comment existing = getById(id);

        if (updated.getText() != null) {
            existing.setText(updated.getText());
        }

        if (updated.getUser() != null && updated.getUser().getId() != null) {
            existing.setUser(updated.getUser());
            existing.getId().setUserId(updated.getUser().getId());
        }
        if (updated.getTask() != null && updated.getTask().getId() != null) {
            existing.setTask(updated.getTask());
            existing.getId().setTaskId(updated.getTask().getId());
        }

        return commentRepository.save(existing);
    }

    @Override
    public void delete(CommentId id) {
        if (!commentRepository.existsById(id)) {
            throw new EntityNotFoundException("Comment not found with id: " + id);
        }
        commentRepository.deleteById(id);
    }
}
