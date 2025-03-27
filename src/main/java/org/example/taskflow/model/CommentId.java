package org.example.taskflow.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
public class CommentId implements Serializable {
    private Long userId;
    private Long taskId;
    private LocalDateTime timeStamp;

    public CommentId() {}

    public CommentId(Long userId, Long taskId, LocalDateTime timeStamp) {
        this.userId = userId;
        this.taskId = taskId;
        this.timeStamp = timeStamp;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
