package com.devsuperior.dscatalog2023.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dscatalog2023.dto.CategoryDTO;
import com.devsuperior.dscatalog2023.entities.Category;
import com.devsuperior.dscatalog2023.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Register the class to be part of the Dependency Injection of Spring
 */
@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Transactional(readOnly = true)
	public List<CategoryDTO> findAll() {
		List<Category> list = repository.findAll();
		return list.stream().map(CategoryDTO::new).collect(Collectors.toList());
	}

	public CategoryDTO findByID(Long id) {
		Optional<Category> optionalEntity = repository.findById(id);
		Category entity = optionalEntity.get();
		return new CategoryDTO(entity);
	}

}
