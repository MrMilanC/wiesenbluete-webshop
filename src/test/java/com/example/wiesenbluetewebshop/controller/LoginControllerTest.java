package com.example.wiesenbluetewebshop.controller;

import com.example.wiesenbluetewebshop.dto.LoginRequest;
import com.example.wiesenbluetewebshop.dto.LoginResponse;
import com.example.wiesenbluetewebshop.security.jwt.JwtIssuer;
import com.example.wiesenbluetewebshop.security.principal.UserPrincipal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LoginControllerTest {

    @InjectMocks
    private LoginController loginController;

    @Mock
    private JwtIssuer jwtIssuer;

    @Mock
    private AuthenticationManager authenticationManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //Mock for mocking

    @Test
    void testLoginValid() {
        // Create valid request for logging
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("validUser");
        loginRequest.setPassword("valid123");

        // Create a mock Authentication object
        Authentication authentication = mock(Authentication.class);

        // mock UserPrincipal
        UserPrincipal userPrincipal = new UserPrincipal(1L, "validUser", "valid123", Collections.emptyList());

        // Mock the way AuthenticationManager works sp we get an Authentication object
        when(authenticationManager.authenticate(any()))
                .thenReturn(authentication);

        // Mock the principal in the SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getPrincipal()).thenReturn(userPrincipal);

        // Mock the JwtIssuer behavior
        when(jwtIssuer.issue(eq(1L), eq("validUser"), any()))
                .thenReturn("mockedJWTToken");

        // Call the login method
        LoginResponse loginResponse = loginController.login(loginRequest);

        // Verify the response
        assertNotNull(loginResponse);
        assertTrue(loginResponse.isOk());
        assertEquals("mockedJWTToken", loginResponse.getAccessToken());
    }

    @Test
    void testLoginInvalid() {
        // Create a valid LoginRequest
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("validUser");
        loginRequest.setPassword("valid123");

        // mock Authentication object
        Authentication authentication = mock(Authentication.class);

        // mock entry valid credentials
        UserPrincipal userPrincipal = new UserPrincipal(1L, "validUser", "valid123", Collections.emptyList());

        // Mock how AuthenticationManager works so we get Authentication object
        when(authenticationManager.authenticate(any()))
                .thenReturn(authentication);

        // Mocking principal of SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authentication.getPrincipal()).thenReturn(userPrincipal);

        // Mock the way JwtIssuer works
        when(jwtIssuer.issue(eq(1L), eq("validUser"), any()))
                .thenReturn("mockedJWTToken");

        // Call login
        LoginResponse response = loginController.login(loginRequest);

        // see if response is valid
        assertNotNull(response);
        assertTrue(response.isOk());
        assertEquals("mockedJWTToken", response.getAccessToken());
    }

}
