package com.example.demo.repository;

import com.example.demo.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepo extends JpaRepository<Budget, Long> {
}
