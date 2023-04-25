package com.devsuperior.dscatalog2023.services;

import java.util.Optional;

import com.devsuperior.dscatalog2023.dto.ProductDTO;
import com.devsuperior.dscatalog2023.entities.Product;
import com.devsuperior.dscatalog2023.repositories.ProductRepository;
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
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest) {
		Page<Product> pageList = repository.findAll(pageRequest);
		return pageList.map(ProductDTO::new);
	}

	@Transactional(readOnly = true)
	public ProductDTO findByID(Long id) {
		Optional<Product> optionalEntity = repository.findById(id);
		Product entity = optionalEntity.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
		return new ProductDTO(entity, entity.getCategories());
	}

	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		Product entity = new Product();
		// entity.setName(dto.getName());
		Product savedEntity = repository.save(entity);
		return new ProductDTO(savedEntity);
	}

	@Transactional
	public ProductDTO update(Long id, ProductDTO dto) {
		try {
			Product entity = repository.getReferenceById(id);
			// entity.setName(dto.getName());
			entity = repository.save(entity);
			return new ProductDTO(entity);
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
