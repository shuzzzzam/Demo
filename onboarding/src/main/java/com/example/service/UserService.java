package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.models.User;
import com.example.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.getReferenceById(id);
    }

    @SuppressWarnings("null")
    public User savUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user, Long userId) {
        @SuppressWarnings("null")
        Optional<User> userFromDB = userRepository.findById(userId);

        User tmpUser = userFromDB.get();
        user.setUserId(tmpUser.getUserId());
        user.setUploadedDocuments(tmpUser.getUploadedDocuments());
        user.setOwnedVehicles(tmpUser.getOwnedVehicles());
        return userRepository.save(user);
    }
}
