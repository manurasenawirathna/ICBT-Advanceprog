/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.service.Test;

import com.mycompany.projectx1.dao.UserDAO;
import com.mycompany.projectx1.model.User;
import com.mycompany.projectx1.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {
    private UserService userService;
    private UserDAO mockUserDAO;

    @BeforeEach
    void setUp() {
        // Mock the UserDAO
        mockUserDAO = mock(UserDAO.class);
        userService = new UserService();
        
        // Inject mockUserDAO into UserService (Modify UserService to allow dependency injection for better testing)
        userService = new UserService(mockUserDAO);
    }

    @Test
    void testRegisterUser_Success() {
        // Given
        String firstName = "John";
        String lastName = "Doe";
        String username = "johndoe";
        String email = "johndoe@example.com";
        String phoneNumber = "123456789";
        String password = "password";

        User mockUser = new User(0, firstName, lastName, username, email, phoneNumber, password, null);
        
        // Simulating successful user insertion
        when(mockUserDAO.insertUser(any(User.class))).thenReturn(true);

        // When
        boolean result = userService.registerUser(firstName, lastName, username, email, phoneNumber, password);

        // Then
        assertTrue(result);
        verify(mockUserDAO, times(1)).insertUser(any(User.class));
    }

    @Test
    void testGetUserByEmailAndPassword_Success() {
        // Given
        String email = "test@example.com";
        String password = "securePassword";
        User mockUser = new User(1, "Test", "User", "testuser", email, "987654321", password, null);

        // Simulating a valid user retrieval
        when(mockUserDAO.getUserByEmailAndPassword(email, password)).thenReturn(mockUser);

        // When
        User result = userService.getUserByEmailAndPassword(email, password);

        // Then
        assertNotNull(result);
        assertEquals(email, result.getEmail());
        verify(mockUserDAO, times(1)).getUserByEmailAndPassword(email, password);
    }

    @Test
    void testGetUserByEmailAndPassword_Failure() {
        // Given
        String email = "wrong@example.com";
        String password = "wrongPassword";

        // Simulating no user found
        when(mockUserDAO.getUserByEmailAndPassword(email, password)).thenReturn(null);

        // When
        User result = userService.getUserByEmailAndPassword(email, password);

        // Then
        assertNull(result);
        verify(mockUserDAO, times(1)).getUserByEmailAndPassword(email, password);
    }
}

