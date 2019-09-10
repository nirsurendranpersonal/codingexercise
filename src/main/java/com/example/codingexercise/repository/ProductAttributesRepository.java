package com.example.codingexercise.repository;

import com.example.codingexercise.model.ProductAttributes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ProductAttributesRepository extends JpaRepository<ProductAttributes, Long> {
    Set<ProductAttributes> findByProductId(Long productId);
}
