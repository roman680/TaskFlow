package org.example.taskflow.service;

import org.example.taskflow.model.User;

import java.util.List;

public interface UserService {
    User create(User user);
    User getById(Long id);
    User update(Long id, User user);
    List<User> getALl();
    void delete(Long id);
}
