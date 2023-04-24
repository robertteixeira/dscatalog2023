package com.devsuperior.dscatalog2023.services;

import java.util.Optional;

import com.devsuperior.dscatalog2023.dto.CategoryDTO;
import com.devsuperior.dscatalog2023.entities.Category;
import com.devsuperior.dscatalog2023.repositories.CategoryRepository;
import com.devsuperior.dscatalog2023.services.exception.DataBaseException;
import com.devsuperior.dscatalog2023.services.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	public Page<CategoryDTO> findAllPaged(PageRequest pageRequest) {
		Page<Category> pageList = repository.findAll(pageRequest);
		return pageList.map(CategoryDTO::new);
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

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id not found " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity Violation ");
		}
	}

}
