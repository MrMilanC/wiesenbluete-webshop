package com.example.wiesenbluetewebshop.service;

import com.example.wiesenbluetewebshop.exception.ProductException;
import com.example.wiesenbluetewebshop.model.Product;
import com.example.wiesenbluetewebshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testViewAllProduct() throws ProductException {
        // Arrange
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        Product product2 = new Product();
        products.add(product1);
        products.add(product2);

        when(productRepository.findAll()).thenReturn(products);

        // Act
        List<Product> result = productService.viewAllProduct();

        // Assert
        assertEquals(products, result);
    }

    @Test
    void testViewAllProductNoProductsFound() {
        // Arrange
        when(productRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertThrows(ProductException.class, () -> productService.viewAllProduct());
    }

    @Test
    void testAddProduct() throws ProductException, IOException {
        // Arrange
        Product product = new Product();

        when(productRepository.save(product)).thenReturn(product);

        // Act
        Product result = productService.addProduct(product);

        // Assert
        assertEquals(product, result);
    }

    @Test
    void testUpdateProduct() throws ProductException {
        // Arrange
        Long productId = 1L;
        Product existingProduct = new Product();
        existingProduct.setProductId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(existingProduct);

        // Act
        Product result = productService.updateProduct(existingProduct);

        // Assert
        assertEquals(existingProduct, result);
    }

    @Test
    void testUpdateProductProductNotFound() {
        // Arrange
        Long productId = 1L;
        Product existingProduct = new Product();
        existingProduct.setProductId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ProductException.class, () -> productService.updateProduct(existingProduct));
    }

    @Test
    void testViewProduct() throws ProductException {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        product.setProductId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        Product result = productService.viewProduct(productId);

        // Assert
        assertEquals(product, result);
    }

    @Test
    void testViewProductProductNotFound() {
        // Arrange
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ProductException.class, () -> productService.viewProduct(productId));
    }

    @Test
    void testRemoveProduct() throws ProductException {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        product.setProductId(productId);

        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        Product result = productService.removeProduct(productId);

        // Assert
        assertEquals(product, result);
        verify(productRepository, times(1)).delete(product);
    }

    @Test
    void testRemoveProductProductNotFound() {
        // Arrange
        Long productId = 1L;

        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ProductException.class, () -> productService.removeProduct(productId));
    }
}
