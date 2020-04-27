package com.example.javatest.service;

import java.util.List;

import com.example.javatest.model.Category;

public interface CategoryService {

	Category save(Category movie);

	List<Category> getAllCategories();
	
	Category findCategory(int categoryId); 

	Category getCategory(String category);
	
	

}
