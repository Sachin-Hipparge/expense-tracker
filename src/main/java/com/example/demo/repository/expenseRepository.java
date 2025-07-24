package com.example.demo.repository;

import com.example.demo.model.expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface expenseRepository extends JpaRepository<expense, Long> {
}
