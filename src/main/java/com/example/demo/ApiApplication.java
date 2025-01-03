package com.example.demo;

import jakarta.annotation.PostConstruct;
import lk.mydentist.api.model.Branch;
import lk.mydentist.api.model.Country;
import lk.mydentist.api.model.User;
import lk.mydentist.api.repository.BranchRepo;
import lk.mydentist.api.repository.CountryRepo;
import lk.mydentist.api.repository.UserRepo;
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
    private final BranchRepo branchRepo;
    private final CountryRepo countryRepo;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    Branch branch;
    Country country;

    @Autowired
    public ApiApplication(UserRepo repository, BranchRepo branchRepo, CountryRepo countryRepo) {
        this.userRepo = repository;
        this.branchRepo = branchRepo;
        this.countryRepo = countryRepo;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    @PostConstruct
    public void initUsers() {
        try {
            String encodePassword = passwordEncoder.encode("1234");
            country = countryRepo.save(new Country(1L, "sri lanka", "s001", "lkr", "DD/MM/YYYY"));

            branch = new Branch(1L, "COL 03", "Colombo", "Colombo", 1, country.getCountryId());
            branch.setCountry(country);
            branch = branchRepo.save(branch);

            userRepo.save(new User(1L, "piyumal", "galle", "nipuna315np@gmail.com", "0754585756", "admin", "199907502281", "piyumal", encodePassword, branch));

        } catch (Exception e) {
            logger.error("An error occurred during user initialization.", e);
        }

    }
}
