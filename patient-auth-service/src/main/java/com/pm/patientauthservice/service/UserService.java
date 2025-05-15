package com.pm.patientauthservice.service;

import com.pm.patientauthservice.model.User;
import com.pm.patientauthservice.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;



@Service
public class UserService {
    public final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findByEmail(String email) {
        // Placeholder for actual implementation
        return userRepository.findByEmail(email);
    }
}
