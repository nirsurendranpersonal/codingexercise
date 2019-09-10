package com.example.codingexercise.repository;

import com.example.codingexercise.model.Product;
import com.example.codingexercise.model.ProductAttributes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql("/data.sql")
public class ProductAttributeRepositoryTest {

    @Autowired
    private ProductAttributesRepository productAttributesRepository;

    @Test
    public void shouldCreateNewProductRepositoryWithSizeFitnessValue_whenProductExists() {
        ProductAttributes productAttributes = new ProductAttributes();
        Product product = new Product();
        product.setName("Test product1");
        product.setId(1L);
        productAttributes.setProduct(product);
        productAttributes.setSizeFitness(1);
        productAttributesRepository.save(productAttributes);

        Set<ProductAttributes> existingProductAttributes = productAttributesRepository.findByProductId(1L);
        assertThat(existingProductAttributes.size(), is(1));
        existingProductAttributes.stream().forEach(pa -> {
            assertThat(pa.getProduct().getId(), is(1L));
        });

    }

}