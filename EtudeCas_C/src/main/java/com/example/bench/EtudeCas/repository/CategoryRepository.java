package com.example.bench.EtudeCas.repository;

import com.example.bench.EtudeCas.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByCode(String code);
}

