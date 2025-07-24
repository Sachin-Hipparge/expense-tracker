package com.example.demo.controller;

import com.example.demo.model.expense;
import com.example.demo.repository.expenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*") // Allow frontend access (important for web apps)
public class expenseController {

    @Autowired
    private expenseRepository repository;

    // GET all expenses
    @GetMapping
    public List<expense> getAllExpenses() {
        return repository.findAll();
    }

    // POST new expense
    @PostMapping
    public expense createExpense(@RequestBody expense exp) {
        return repository.save(exp);
    }

    // PUT update existing expense
    @PutMapping("/{id}")
    public expense updateExpense(@PathVariable Long id, @RequestBody expense exp) {
        expense existing = repository.findById(id).orElseThrow();
        existing.setTitle(exp.getTitle());
        existing.setAmount(exp.getAmount());
        existing.setCategory(exp.getCategory());
        existing.setDate(exp.getDate());
        return repository.save(existing);
    }

    // DELETE expense by ID
    
    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
