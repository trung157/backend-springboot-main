package com.spring.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.demo.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
