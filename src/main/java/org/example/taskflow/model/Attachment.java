package org.example.taskflow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "attachments")
public class Attachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long taskId;

    @NotBlank
    private String dropboxFileId;

    @NotBlank
    private String filename;

    private LocalDateTime uploadDate = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotNull Long getTaskId() {
        return taskId;
    }

    public void setTaskId(@NotNull Long taskId) {
        this.taskId = taskId;
    }

    public @NotBlank String getDropboxFileId() {
        return dropboxFileId;
    }

    public void setDropboxFileId(@NotBlank String dropboxFileId) {
        this.dropboxFileId = dropboxFileId;
    }

    public @NotBlank String getFilename() {
        return filename;
    }

    public void setFilename(@NotBlank String filename) {
        this.filename = filename;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }
}
