package com.example.demo.service;

import com.example.demo.dto.BudgetDto;
import com.example.demo.dto.get.BudgetDtoGet;

import java.util.List;

public interface BudgetService {
    BudgetDto save(BudgetDto budget);

    BudgetDto update(Long id, BudgetDto budget);

    BudgetDto delete(Long id);

    List<BudgetDtoGet> getAll();

    Object search(Long id);
}
