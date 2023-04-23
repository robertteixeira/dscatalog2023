package com.devsuperior.dscatalog2023.services;

import java.util.List;

import com.devsuperior.dscatalog2023.entities.Category;
import com.devsuperior.dscatalog2023.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Register the class to be part of the Dependency Injection of Spring
 */
@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public List<Category> findAll() {
		return repository.findAll();
	}

}
