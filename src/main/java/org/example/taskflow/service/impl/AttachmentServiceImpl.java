package org.example.taskflow.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.taskflow.model.Attachment;
import org.example.taskflow.repository.AttachmentRepository;
import org.example.taskflow.service.AttachmentService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService {
    private final AttachmentRepository attachmentRepository;

    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public Attachment create(Attachment attachment) {
        return attachmentRepository.save(attachment);
    }

    @Override
    public Attachment getById(Long id) {
        return attachmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attachment not found with id: " + id));
    }

    @Override
    public List<Attachment> getAll() {
        return attachmentRepository.findAll();
    }

    @Override
    public Attachment update(Long id, Attachment updated) {
        Attachment fromDb = getById(id);
        if (updated.getDropboxFileId() != null) {
            fromDb.setDropboxFileId(updated.getDropboxFileId());
        }
        if (updated.getFilename() != null) {
            fromDb.setFilename(updated.getFilename());
        }
        if (updated.getTask() != null) {
            fromDb.setTask(updated.getTask());
        }
        return attachmentRepository.save(fromDb);
    }

    @Override
    public void delete(Long id) {
        if (!attachmentRepository.existsById(id)) {
            throw new EntityNotFoundException("Attachment not found with id: " + id);
        }
        attachmentRepository.deleteById(id);
    }
}
