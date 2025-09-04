package org.example.taskflow.service.impl;

import org.example.taskflow.model.User;
import org.example.taskflow.repository.UserRepository;
import org.example.taskflow.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with id: " + id));
    }

    @Override
    public User update(Long id, User updated) {
        User userFromDb = getById(id);

        if (updated.getUsername() != null) {
            userFromDb.setUsername(updated.getUsername());
        }
        if (updated.getPassword() != null) {
            userFromDb.setPassword(updated.getPassword());
        }
        if (updated.getEmail() != null) {
            userFromDb.setEmail(updated.getEmail());
        }
        if (updated.getFirstName() != null) {
            userFromDb.setFirstName(updated.getFirstName());
        }
        if (updated.getLastName() != null) {
            userFromDb.setLastName(updated.getLastName());
        }

        return userRepository.save(userFromDb);
    }

    @Override
    public List<User> getALl() {
        return userRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        if (!userRepository.existsById(1L)) {
            throw new RuntimeException("User not found with id: " + 1L);
        }
        userRepository.deleteById(id);
    }
}
