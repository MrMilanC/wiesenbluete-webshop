package com.example.wiesenbluetewebshop.controller;

import com.example.wiesenbluetewebshop.exception.ProductException;
import com.example.wiesenbluetewebshop.exception.UserException;
import com.example.wiesenbluetewebshop.model.Product;
import com.example.wiesenbluetewebshop.model.User;
import com.example.wiesenbluetewebshop.service.ProductService;
import com.example.wiesenbluetewebshop.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class AdminControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private UserService userService;

    @InjectMocks
    private AdminController adminController;

    public AdminControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testViewAllProduct() throws ProductException {
        // Mock the ProductService to return a list of products
        List<Product> productList = Collections.singletonList(new Product());
        when(productService.viewAllProduct()).thenReturn(productList);

        // Call the controller method
        ResponseEntity<List<Product>> response = adminController.viewAllProduct();

        // Assert the response status is OK and the list is not empty
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productList, response.getBody());
    }

    @Test
    void testViewAllUser() throws UserException {
        // Mock the UserService to return a list of users
        List<User> userList = Collections.singletonList(new User());
        when(userService.viewAllUser()).thenReturn(userList);

        // Call the controller method
        ResponseEntity<List<User>> response = adminController.viewAllUser();

        // Assert the response status is OK and the list is not empty
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }
}
