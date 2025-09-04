package org.example.taskflow.service;

import org.example.taskflow.model.Attachment;
import java.util.List;

public interface AttachmentService {
    Attachment create(Attachment attachment);
    Attachment getById(Long id);
    List<Attachment> getAll();
    Attachment update(Long id, Attachment updated);
    void delete(Long id);
}
