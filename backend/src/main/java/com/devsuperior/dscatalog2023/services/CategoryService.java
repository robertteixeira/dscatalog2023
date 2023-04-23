package com.devsuperior.dscatalog2023.services;

import java.util.List;
import java.util.Optional;

import com.devsuperior.dscatalog2023.dto.CategoryDTO;
import com.devsuperior.dscatalog2023.entities.Category;
import com.devsuperior.dscatalog2023.repositories.CategoryRepository;
import com.devsuperior.dscatalog2023.services.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;

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
		return list.stream().map(CategoryDTO::new).toList();
	}

	public CategoryDTO findByID(Long id) {
		Optional<Category> optionalEntity = repository.findById(id);
		Category entity = optionalEntity.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
		return new CategoryDTO(entity);
	}

	@Transactional
	public CategoryDTO insert(CategoryDTO dto) {
		Category entity = new Category();
		entity.setName(dto.getName());
		Category savedEntity = repository.save(entity);
		return new CategoryDTO(savedEntity);
	}

	@Transactional
	public CategoryDTO update(Long id, CategoryDTO dto) {
		try {
			Category entity = repository.getReferenceById(id);
			entity.setName(dto.getName());
			entity = repository.save(entity);
			return new CategoryDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		}
	}

}
