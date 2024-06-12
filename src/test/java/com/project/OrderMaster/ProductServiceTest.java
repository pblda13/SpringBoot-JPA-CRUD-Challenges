package com.project.OrderMaster;

import com.project.OrderMaster.entity.Product;
import com.project.OrderMaster.repository.ProductRepository;
import com.project.OrderMaster.service.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductServiceTest {

    private ProductRepository productRepository = Mockito.mock(ProductRepository.class);
    private ProductService productService = new ProductService();

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.setName("Test Product");
        product.setDescription("Test Description");
        product.setPrice(100.0);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals("Test Product", createdProduct.getName());
    }
}
