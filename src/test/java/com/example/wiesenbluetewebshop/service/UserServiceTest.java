package com.example.wiesenbluetewebshop.service;

import com.example.wiesenbluetewebshop.exception.UserException;
import com.example.wiesenbluetewebshop.model.User;
import com.example.wiesenbluetewebshop.repository.UserRepository;
import com.example.wiesenbluetewebshop.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDoesUsernameExist() {
        // Arrange
        String username = "testUser";

        when(userRepository.existsByUsername(username)).thenReturn(true);

        // Act
        boolean result = userService.doesUsernameExist(username);

        // Assert
        assertTrue(result);
    }

    @Test
    void testFindUserByUsername() {
        // Arrange
        String username = "testUser";
        User user = new User();
        user.setUsername(username);

        when(userRepository.findByUsername(username)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.findByUsername(username);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(username, result.get().getUsername());
    }

    @Test
    void testAddUser() throws UserException {
        // Arrange
        User user = new User();

        when(userRepository.save(user)).thenReturn(user);

        // Act
        User result = userService.addUser(user);

        // Assert
        assertEquals(user, result);
    }

    @Test
    void testUpdateUser() throws UserException {
        // Arrange
        Long userId = 1L;
        User existingUser = new User();
        existingUser.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(existingUser);

        // Act
        User result = userService.updateUser(existingUser);

        // Assert
        assertEquals(existingUser, result);
    }

    @Test
    void testUpdateUserUserNotFound() {
        // Arrange
        Long userId = 1L;
        User existingUser = new User();
        existingUser.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UserException.class, () -> userService.updateUser(existingUser));
    }

    @Test
    void testRemoveUser() throws UserException {
        // Arrange
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        User result = userService.removeUser(userId);

        // Assert
        assertEquals(user, result);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testRemoveUserUserNotFound() {
        // Arrange
        Long userId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UserException.class, () -> userService.removeUser(userId));
    }

    @Test
    void testViewAllUser() throws UserException {
        // Arrange
        List<User> users = new ArrayList<>();
        User user1 = new User();
        User user2 = new User();
        users.add(user1);
        users.add(user2);

        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<User> result = userService.viewAllUser();

        // Assert
        assertEquals(users, result);
    }

    @Test
    void testViewAllUserNoUsersFound() {
        // Arrange
        when(userRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertThrows(UserException.class, () -> userService.viewAllUser());
    }
}
