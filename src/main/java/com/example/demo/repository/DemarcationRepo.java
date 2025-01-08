package com.example.demo.repository;

import com.example.demo.model.Demarcation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DemarcationRepo extends JpaRepository<Demarcation, Long> {
}
