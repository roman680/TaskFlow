package org.example.taskflow.service.impl;

import jakarta.persistence.EntityNotFoundException;
import org.example.taskflow.model.Label;
import org.example.taskflow.repository.LabelRepository;
import org.example.taskflow.service.LabelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabelServiceImpl implements LabelService {
    private final LabelRepository labelRepository;

    public LabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public Label create(Label label) {
        return labelRepository.save(label);
    }

    @Override
    public Label getById(Long id) {
        return labelRepository.findById(id).orElseThrow(()->
                new EntityNotFoundException("Label not found with id: " + id));
    }

    @Override
    public List<Label> getAll() {
        return labelRepository.findAll();
    }

    @Override
    public Label update(Long id, Label updated) {
        Label labelFromDb = getById(id);
        if (updated.getName() != null) {
            labelFromDb.setName(updated.getName());
        }
        if (updated.getColor() != null) {
            labelFromDb.setColor(updated.getColor());
        }
        return labelRepository.save(labelFromDb);
    }

    @Override
    public void delete(Long id) {
        if (!labelRepository.existsById(id)) {
            throw new EntityNotFoundException("Label not found with id: " + id);
        }
        labelRepository.deleteById(id);
    }
}
