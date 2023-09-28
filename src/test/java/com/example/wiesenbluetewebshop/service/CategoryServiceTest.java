package com.example.wiesenbluetewebshop.service;

import com.example.wiesenbluetewebshop.exception.ProductException;
import com.example.wiesenbluetewebshop.model.Category;
import com.example.wiesenbluetewebshop.model.Product;
import com.example.wiesenbluetewebshop.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testViewProductByCategory() throws ProductException {
        // Arrange
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setId(1L);
        products.add(product1);
        category.setProductList(products);

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));

        // Act
        List<Product> result = categoryService.viewProductByCategory(categoryId);

        // Assert
        assertEquals(products, result);
    }

    @Test
    void testViewProductByCategoryCategoryNotFound() {
        // Arrange
        Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ProductException.class, () -> categoryService.viewProductByCategory(categoryId));
    }
}
