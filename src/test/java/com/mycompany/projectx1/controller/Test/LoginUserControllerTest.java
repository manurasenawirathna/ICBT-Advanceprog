/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.controller.Test;

import com.mycompany.projectx1.controller.LoginUserController;
import com.mycompany.projectx1.service.UserService;
import com.mycompany.projectx1.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

class LoginUserControllerTest {
    private LoginUserController controller;

    @Mock
    private UserService mockUserService;
    @Mock
    private HttpServletRequest mockRequest;
    @Mock
    private HttpServletResponse mockResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new LoginUserController(mockUserService); // ✅ Inject mock UserService
    }

    @Test
    void testDoPost_SuccessfulLogin() throws ServletException, IOException {
        // ✅ Mock form parameters
        when(mockRequest.getParameter("email")).thenReturn("johndoe@example.com");
        when(mockRequest.getParameter("password")).thenReturn("password123");

        // ✅ Mock service behavior
        User mockUser = new User(1, "John", "Doe", "johndoe", "johndoe@example.com", "1234567890", "password123", null);
        when(mockUserService.getUserByEmailAndPassword("johndoe@example.com", "password123")).thenReturn(mockUser);

        // ✅ Call the method
        controller.doPost(mockRequest, mockResponse);

        // ✅ Verify redirection
        verify(mockResponse, times(1)).sendRedirect("pages/login.jsp?success=1");
    }

    @Test
    void testDoPost_InvalidCredentials() throws ServletException, IOException {
        when(mockRequest.getParameter("email")).thenReturn("invalid@example.com");
        when(mockRequest.getParameter("password")).thenReturn("wrongpassword");

        when(mockUserService.getUserByEmailAndPassword("invalid@example.com", "wrongpassword")).thenReturn(null);

        controller.doPost(mockRequest, mockResponse);

        verify(mockResponse, times(1)).sendRedirect("pages/login.jsp?error=1");
    }
}

