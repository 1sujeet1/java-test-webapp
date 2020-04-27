package com.example.javatest.service.impl;

import java.util.List;

import com.example.javatest.model.Category;
import com.example.javatest.repository.CategoryRepository;
import com.example.javatest.service.CategoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findCategory(int categoryId) {
        Category category = categoryRepository.findById(categoryId);
		return (category!=null ? category : null);
    }

    @Override
    public Category getCategory(String category) {
        return categoryRepository.getCategory(category);
    }

   

}
