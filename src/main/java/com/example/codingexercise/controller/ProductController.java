package com.example.codingexercise.controller;

import com.example.codingexercise.exception.ProductNotFoundException;
import com.example.codingexercise.model.ProductAttributes;
import com.example.codingexercise.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private ProductService productService;

    @PostMapping("/products/{id}/addTrueToSize")
    public ResponseEntity<String> addTrueToSizeEntry(@PathVariable Long id, @Valid @RequestBody ProductAttributes productAttributes)
            throws ProductNotFoundException {
        productService.createProductAttributes(id, productAttributes);
        logger.info(String.format("Successfully saved ProductAttributes of product id %s", id));
        return new ResponseEntity<>("Successfully updated!",  HttpStatus.OK);
    }

    @GetMapping("/products/{id}/averageTrueToSize")
    public ResponseEntity<Double> getCalculatedTrueToSize(@PathVariable Long id) {
        Double averageTrueToSize = productService.calculateAverageTrueToSize(id);
        logger.info(String.format("Calculated average TrueToSize value of product id %s", id));
        return  ResponseEntity.ok(averageTrueToSize);
    }
}
