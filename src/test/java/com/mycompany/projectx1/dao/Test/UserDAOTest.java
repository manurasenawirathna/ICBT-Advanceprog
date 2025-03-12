/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.dao.Test;

import com.mycompany.projectx1.dao.UserDAO;
import com.mycompany.projectx1.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserDAOTest {
    private UserDAO userDAO;
    
    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;

    private MockedStatic<DriverManager> mockedDriverManager; // ✅ Static Mock

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        userDAO = new UserDAO();

        // ✅ Mock DriverManager to return a mocked connection
        mockedDriverManager = mockStatic(DriverManager.class);
        mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                           .thenReturn(mockConnection);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    }

    @AfterEach
    void tearDown() {
        mockedDriverManager.close(); // ✅ Release static mock to avoid conflicts
    }

    @Test
    void testInsertUser_Success() throws Exception {
        // Given
        User user = new User(0, "John", "Doe", "johndoe", "john@example.com", "1234567890", "securePass", null);

        // Mocking behavior
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // When
        boolean result = userDAO.insertUser(user);

        // Then
        assertTrue(result);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testInsertUser_Failure() throws Exception {
        // Given
        User user = new User(0, "Jane", "Doe", "janedoe", "jane@example.com", "0987654321", "weakPass", null);

        // Mocking behavior
        when(mockPreparedStatement.executeUpdate()).thenReturn(0);

        // When
        boolean result = userDAO.insertUser(user);

        // Then
        assertFalse(result);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testGetUserByEmailAndPassword_Success() throws Exception {
        // Given
        String email = "john@example.com";
        String password = "securePass";

        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("first_name")).thenReturn("John");
        when(mockResultSet.getString("last_name")).thenReturn("Doe");
        when(mockResultSet.getString("username")).thenReturn("johndoe");
        when(mockResultSet.getString("email")).thenReturn(email);
        when(mockResultSet.getString("phone_number")).thenReturn("1234567890");
        when(mockResultSet.getString("password")).thenReturn(password);

        // When
        User result = userDAO.getUserByEmailAndPassword(email, password);

        // Then
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals(email, result.getEmail());
        verify(mockPreparedStatement, times(1)).executeQuery();
    }

    @Test
    void testGetUserByEmailAndPassword_NotFound() throws Exception {
        // Given
        String email = "invalid@example.com";
        String password = "wrongPass";

        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false); // No user found

        // When
        User result = userDAO.getUserByEmailAndPassword(email, password);

        // Then
        assertNull(result);
        verify(mockPreparedStatement, times(1)).executeQuery();
    }
}


