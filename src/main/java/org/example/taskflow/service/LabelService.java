package org.example.taskflow.service;

import org.example.taskflow.model.Label;
import java.util.List;

public interface LabelService {
    Label create(Label label);
    Label getById(Long id);
    List<Label> getAll();
    Label update(Long id, Label updated);
    void delete(Long id);
}
