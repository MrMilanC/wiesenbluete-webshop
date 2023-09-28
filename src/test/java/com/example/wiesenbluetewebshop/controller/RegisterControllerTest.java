package com.example.wiesenbluetewebshop.controller;

import com.example.wiesenbluetewebshop.dto.NewUserDTO;
import com.example.wiesenbluetewebshop.exception.UserException;
import com.example.wiesenbluetewebshop.model.User;
import com.example.wiesenbluetewebshop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@SpringBootTest
class RegisterControllerTest {

    @InjectMocks
    private RegisterController registerController;

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //We are using mockito for mocking user habits

    @Test
    void testRegisterUserValid() throws UserException {
        // Create a NewUserDTO that contains valid data
        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setFirstName("Max");
        newUserDTO.setLastName("Mustermann");
        newUserDTO.setUsername("maxmustermann");
        newUserDTO.setEmail("mm@example.com");
        newUserDTO.setPassword("passwort123");

        // Mock userService and passwordEncoder way of behaving
        when(userService.doesUsernameExist(newUserDTO.getUsername())).thenReturn(false);

        // this user object is necessary so the controller can return it
        User mockUser = new User();
        mockUser.setUsername(newUserDTO.getUsername());

        // Mocking addUser method so we can return the user
        when(userService.addUser(any(User.class))).thenReturn(mockUser);

        // Calling registerUser method
        ResponseEntity<?> response = registerController.registerUser(newUserDTO);

        // Verifying HTTP response 200
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User is registered successfully!", response.getBody());
    }

    @Test
    void testRegisterExistingUsername() throws UserException {
        // Creating a NewUserDTO with a username that already exists
        NewUserDTO newUserDTO = new NewUserDTO();
        newUserDTO.setUsername("existinguser");

        // Mocking userService to return boolean, that username exists already
        when(userService.doesUsernameExist(newUserDTO.getUsername())).thenReturn(true);

        // Calling registerUser
        ResponseEntity<?> response = registerController.registerUser(newUserDTO);

        // Verifying that it is an HTTP 400
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Username already exists!", response.getBody());
    }
}
