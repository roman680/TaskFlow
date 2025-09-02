package org.example.taskflow.service;

import org.example.taskflow.model.Comment;
import org.example.taskflow.model.CommentId;
import java.util.List;

public interface CommentService {
    Comment create(Comment comment);
    Comment getById(CommentId id);
    List<Comment> getAll();
    Comment update(CommentId id, Comment updated);
    void delete(CommentId id);
}
