package com.example.demo;

import com.example.demo.model.Demarcation;
import com.example.demo.model.User;
import com.example.demo.repository.DemarcationRepo;
import com.example.demo.repository.UserRepo;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ApiApplication {
    private static final Logger logger = LoggerFactory.getLogger(ApiApplication.class);
    private final UserRepo userRepo;
    private final DemarcationRepo demarcationRepo;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Autowired
    public ApiApplication(UserRepo repository, DemarcationRepo demarcationRepo) {
        this.userRepo = repository;
        this.demarcationRepo = demarcationRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @PostConstruct
    public void initUsers() {
        try {
            Demarcation demarcation = demarcationRepo.save(new Demarcation(1L, "Galle"));
            String encodePassword = passwordEncoder.encode("1234");
            userRepo.save(new User(1L, "piyumal", "galle", "nipuna315np@gmail.com", "0754585756", "admin", "199907502281", "piyumal", encodePassword,demarcation));

        } catch (Exception e) {
            logger.error("An error occurred during user initialization.", e);
        }

    }
}
