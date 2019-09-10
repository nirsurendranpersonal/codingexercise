package com.example.codingexercise.integrationTests.service;

import com.example.codingexercise.exception.ProductNotFoundException;
import com.example.codingexercise.model.ProductAttributes;
import com.example.codingexercise.model.Product;
import com.example.codingexercise.repository.ProductAttributesRepository;
import com.example.codingexercise.repository.ProductRepository;
import com.example.codingexercise.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository mockProductRepository;

    @Mock
    private ProductAttributesRepository mockProductAttributesRepository;

    @Before
    public void init() {
        productService = new ProductService(mockProductRepository, mockProductAttributesRepository);
    }

    @Test
    public void calculateAverageTrueToSize_whenProductExists() {
        when(mockProductAttributesRepository.findByProductId(1L)).thenReturn(getMockProductAttributesList(1L));
        Double averageTrueToSizeValue = productService.calculateAverageTrueToSize(1L);
        assertThat(averageTrueToSizeValue, is(2.5714285714285716));
    }

    @Test(expected= ProductNotFoundException.class)
    public void averageTrueToSizeCalculation_throwException_whenProductDoesNotExist() {
        productService.calculateAverageTrueToSize(-999L);
    }

    @Test
    public void addSizeFitness_whenProductExists() {
        ProductAttributes mockProductAttributes = new ProductAttributes();
        Product mockProduct = new Product();
        mockProduct.setId(1L);

        when(mockProductAttributesRepository.save(mockProductAttributes)).thenAnswer(i->i.getArguments()[0]);
        when(mockProductRepository.findById(1L)).thenReturn(Optional.of(mockProduct));

        productService.createProductAttributes(1L, mockProductAttributes);
        //verify the attributes are assigned to the product
        assertThat(mockProductAttributes.getProduct().getId(), is(1L));
    }

    @Test(expected=ProductNotFoundException.class)
    public void addSizeFitness_throwException_whenProductDoesNotExist() {
        ProductAttributes mockProductAttributes = new ProductAttributes();
        productService.createProductAttributes(-999L, mockProductAttributes);
    }

    private Set<ProductAttributes> getMockProductAttributesList(Long productId) {
        Set<ProductAttributes> productAttributesSet  = new HashSet<>();
        ProductAttributes attributes = new ProductAttributes();
        //Sample set of values: 1, 2, 2, 3, 2, 3, 2, 2, 3, 4, 2, 5, 2, 3
        Product product = new Product();
        product.setId(productId);

        attributes.setSizeFitness(1);
        attributes.setProduct(product);
        productAttributesSet.add(attributes);

        attributes = new ProductAttributes();
        attributes.setSizeFitness(2);
        attributes.setProduct(product);
        productAttributesSet.add(attributes);

        attributes = new ProductAttributes();
        attributes.setSizeFitness(2);
        attributes.setProduct(product);
        productAttributesSet.add(attributes);

        attributes = new ProductAttributes();
        attributes.setSizeFitness(3);
        attributes.setProduct(product);
        productAttributesSet.add(attributes);

        attributes = new ProductAttributes();
        attributes.setSizeFitness(2);
        attributes.setProduct(product);
        productAttributesSet.add(attributes);

        attributes = new ProductAttributes();
        attributes.setSizeFitness(3);
        attributes.setProduct(product);
        productAttributesSet.add(attributes);

        attributes = new ProductAttributes();
        attributes.setSizeFitness(2);
        attributes.setProduct(product);
        productAttributesSet.add(attributes);

        attributes = new ProductAttributes();
        attributes.setSizeFitness(2);
        attributes.setProduct(product);
        productAttributesSet.add(attributes);

        attributes = new ProductAttributes();
        attributes.setSizeFitness(3);
        attributes.setProduct(product);
        productAttributesSet.add(attributes);

        attributes = new ProductAttributes();
        attributes.setSizeFitness(4);
        attributes.setProduct(product);
        productAttributesSet.add(attributes);

        attributes = new ProductAttributes();
        attributes.setSizeFitness(2);
        attributes.setProduct(product);
        productAttributesSet.add(attributes);

        attributes = new ProductAttributes();
        attributes.setSizeFitness(5);
        attributes.setProduct(product);
        productAttributesSet.add(attributes);

        attributes = new ProductAttributes();
        attributes.setSizeFitness(2);
        attributes.setProduct(product);
        productAttributesSet.add(attributes);

        attributes = new ProductAttributes();
        attributes.setSizeFitness(3);
        attributes.setProduct(product);
        productAttributesSet.add(attributes);

        return productAttributesSet;
    }
}
