package com.example.demo.config;

import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private static final Logger log = LoggerFactory.getLogger(DataInitializer.class);
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initData(UserRepository userRepository) {
        return args -> {
            if (userRepository.count() == 0) {
                log.info("Duke inicializuar te dhenat e perdoruesve...");

                UserEntity admin = new UserEntity();
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setName("Administrator");
                admin.setRole("ADMIN");
                admin.setEnabled(true);
                userRepository.save(admin);

                UserEntity user = new UserEntity();
                user.setEmail("user@example.com");
                user.setPassword(passwordEncoder.encode("user123"));
                user.setName("Test User");
                user.setRole("USER");
                user.setEnabled(true);
                userRepository.save(user);

                log.info("Te dhenat u inicializuan me sukses!");
                log.info("Admin: admin@example.com / admin123");
                log.info("User: user@example.com / user123");
            }
        };
    }
}

