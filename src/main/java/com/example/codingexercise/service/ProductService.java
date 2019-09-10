package com.example.codingexercise.service;

import com.example.codingexercise.exception.ProductNotFoundException;
import com.example.codingexercise.model.Product;
import com.example.codingexercise.model.ProductAttributes;
import com.example.codingexercise.repository.ProductAttributesRepository;
import com.example.codingexercise.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private ProductAttributesRepository productAttributesRepository;

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductAttributesRepository productAttributesRepository) {
        this.productAttributesRepository = productAttributesRepository;
        this.productRepository = productRepository;
    }

    public void createProductAttributes(Long productId, ProductAttributes productAttributes) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(String.format("No product record found for id %s", productId)));

        productAttributes.setProduct(product);
        productAttributesRepository.save(productAttributes);
        logger.info(String.format("Successfully saved ProductAttributes for product id %s", productId));
    }

    public Double calculateAverageTrueToSize(Long productId) throws ProductNotFoundException {
        Set<ProductAttributes> attributes = productAttributesRepository.findByProductId(productId);

        return attributes.stream()
                .mapToDouble(ProductAttributes::getSizeFitness)
                .average()
                .orElseThrow(() -> new ProductNotFoundException(String.format("No product attributes found for id %s", productId )));
    }
}
