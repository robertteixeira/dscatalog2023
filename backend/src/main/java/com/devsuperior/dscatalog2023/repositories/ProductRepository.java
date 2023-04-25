package com.devsuperior.dscatalog2023.repositories;

import com.devsuperior.dscatalog2023.entities.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
