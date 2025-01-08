package com.example.demo.controller;

import com.example.demo.dto.BudgetDto;
import com.example.demo.dto.get.BudgetDtoGet;
import com.example.demo.service.BudgetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("budget")
@CrossOrigin
public class BudgetController {
    private final BudgetService budgetService;

    public BudgetController(BudgetService budgetService) {
        this.budgetService = budgetService;
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody BudgetDto budget) {
        BudgetDto savedBudget = budgetService.save(budget);
        return new ResponseEntity<>(savedBudget, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody BudgetDto budget) {
        BudgetDto updatedBudget = budgetService.update(id, budget);
        return updatedBudget != null ? new ResponseEntity<>(updatedBudget, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        BudgetDto deletedBudget = budgetService.delete(id);
        return deletedBudget != null ? new ResponseEntity<>(deletedBudget, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        List<BudgetDtoGet> budgets = budgetService.getAll();
        return new ResponseEntity<>(budgets, HttpStatus.OK);
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<Object> search(@PathVariable Long id) {
        Object budget = budgetService.search(id);
        return budget != null ? new ResponseEntity<>(budget, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
