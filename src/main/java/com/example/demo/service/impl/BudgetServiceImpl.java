package com.example.demo.service.impl;

import com.example.demo.dto.BudgetDto;
import com.example.demo.dto.get.BudgetDtoGet;
import com.example.demo.model.Budget;
import com.example.demo.repository.BudgetRepo;
import com.example.demo.service.BudgetService;
import com.example.demo.util.ModelMapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetServiceImpl implements BudgetService {
    private final BudgetRepo budgetRepo;
    private final ModelMapperConfig modelMapperConfig;
    private final UserServiceImpl userService;

    @Autowired
    public BudgetServiceImpl(BudgetRepo budgetRepo, ModelMapperConfig modelMapperConfig, UserServiceImpl userService) {
        this.budgetRepo = budgetRepo;
        this.modelMapperConfig = modelMapperConfig;
        this.userService = userService;
    }

    @Override
    public BudgetDto save(BudgetDto dto) {
            return entityToDto(budgetRepo.save(dtoToEntity(dto)));
    }

    @Override
    public BudgetDto update(Long id, BudgetDto dto) {
        Optional<Budget> existingBudget = budgetRepo.findById(id);
        if (existingBudget.isPresent()) {
            dto.setId(id);
            return entityToDto(budgetRepo.save(dtoToEntity(dto)));
        }
        return null;
    }

    @Override
    public BudgetDto delete(Long id) {
        Optional<Budget> budget = budgetRepo.findById(id);
        if (budget.isPresent()) {
            budgetRepo.deleteById(id);
            return entityToDto(budget.get());
        }
        return null;
    }

    @Override
    public List<BudgetDtoGet> getAll() {
        List<Budget> budgets = budgetRepo.findAll();
        List<BudgetDtoGet> dtoList = new ArrayList<>();
        for (Budget budget : budgets) {
            dtoList.add(entityToDtoGet(budget));
        }
        return dtoList;
    }

    @Override
    public Object search(Long id) {
        Optional<Budget> budget = budgetRepo.findById(id);
        return budget.map(this::entityToDtoGet).orElse(null);
    }

    private Budget dtoToEntity(BudgetDto dto) {
        return modelMapperConfig.modelMapper().map(dto, Budget.class);
    }

    private BudgetDto entityToDto(Budget budget) {
        return modelMapperConfig.modelMapper().map(budget, BudgetDto.class);
    }

    private BudgetDtoGet entityToDtoGet(Budget budget) {
        BudgetDtoGet map = modelMapperConfig.modelMapper().map(budget, BudgetDtoGet.class);
        map.setUserId(userService.entityToDtoGet(budget.getUserId()));
        return map;
    }

}
