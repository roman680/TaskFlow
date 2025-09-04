package org.example.taskflow.repository;

import org.example.taskflow.model.Comment;
import org.example.taskflow.model.CommentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, CommentId> {
    List<Comment> findByTaskId(Long taskId);
}
