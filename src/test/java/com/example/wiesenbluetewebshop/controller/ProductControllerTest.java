package com.example.wiesenbluetewebshop.controller;

import com.example.wiesenbluetewebshop.exception.ProductException;
import com.example.wiesenbluetewebshop.model.Product;
import com.example.wiesenbluetewebshop.service.CategoryService;
import com.example.wiesenbluetewebshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testViewAllProduct() throws ProductException {
        // Arrange
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, "Product 1", 10.0));
        productList.add(new Product(2L, "Product 2", 20.0));

        when(productService.viewAllProduct()).thenReturn(productList);

        // Act
        ResponseEntity<List<Product>> response = productController.viewAllProduct();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
    }

    @Test
    void testViewProductByCategoryId() throws ProductException {
        // Arrange
        Long categoryId = 1L;
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1L, "Product 1", 10.0));
        productList.add(new Product(2L, "Product 2", 20.0));

        when(categoryService.viewProductByCategory(categoryId)).thenReturn(productList);

        // Act
        ResponseEntity<List<Product>> response = productController.viewProductByCategoryId(categoryId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
    }
}
