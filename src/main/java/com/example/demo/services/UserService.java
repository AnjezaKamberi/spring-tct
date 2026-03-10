package com.example.demo.services;

import com.example.demo.entities.UserEntity;
import com.example.demo.models.UserDTO;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserDTO registerUser(UserDTO userDTO) {
        log.info("Po regjistrohet perdoruesi me email: {}", userDTO.getEmail());

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("Email " + userDTO.getEmail() + " eshte ne perdorim");
        }

        UserEntity user = new UserEntity();
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setName(userDTO.getName());
        user.setRole(userDTO.getRole() != null ? userDTO.getRole() : "USER");
        user.setEnabled(true);

        UserEntity savedUser = userRepository.save(user);
        log.info("Perdoruesi u regjistrua me sukses: {}", savedUser.getEmail());

        UserDTO response = new UserDTO();
        response.setId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setName(savedUser.getName());
        response.setRole(savedUser.getRole());

        return response;
    }
}

