package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    List<User> findByUserName(String username);

    User findUserById(Long id);

    User findUserByEmailAndUserName(String email, String userName);

    List<User> findByUserNameAndRole(String userName, String role);
}
