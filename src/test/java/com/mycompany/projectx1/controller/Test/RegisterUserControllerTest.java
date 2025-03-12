/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.controller.Test;

import com.mycompany.projectx1.controller.RegisterUserController;
import com.mycompany.projectx1.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

class RegisterUserControllerTest {
    private RegisterUserController controller;

    @Mock
    private UserService mockUserService;
    @Mock
    private HttpServletRequest mockRequest;
    @Mock
    private HttpServletResponse mockResponse;
    @Mock
    private RequestDispatcher mockDispatcher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new RegisterUserController(mockUserService); // ✅ Inject mock UserService
    }

    @Test
    void testDoPost_SuccessfulRegistration() throws ServletException, IOException {
        // ✅ Mock form parameters
        when(mockRequest.getParameter("first-name")).thenReturn("John");
        when(mockRequest.getParameter("last-name")).thenReturn("Doe");
        when(mockRequest.getParameter("username")).thenReturn("johndoe");
        when(mockRequest.getParameter("email")).thenReturn("johndoe@example.com");
        when(mockRequest.getParameter("mobile")).thenReturn("1234567890");
        when(mockRequest.getParameter("password")).thenReturn("password123");

        // ✅ Mock service behavior
        when(mockUserService.registerUser(anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(true);

        // ✅ Call the method
        controller.doPost(mockRequest, mockResponse);

        // ✅ Verify redirection
        verify(mockResponse, times(1)).sendRedirect("pages/register.jsp?success=1&msg=Registration+Successful");
    }

    @Test
    void testDoPost_RegistrationFailure() throws ServletException, IOException {
        when(mockRequest.getParameter("first-name")).thenReturn("John");
        when(mockRequest.getParameter("last-name")).thenReturn("Doe");
        when(mockRequest.getParameter("username")).thenReturn("johndoe");
        when(mockRequest.getParameter("email")).thenReturn("johndoe@example.com");
        when(mockRequest.getParameter("mobile")).thenReturn("1234567890");
        when(mockRequest.getParameter("password")).thenReturn("password123");

        when(mockUserService.registerUser(anyString(), anyString(), anyString(), anyString(), anyString(), anyString())).thenReturn(false);

        controller.doPost(mockRequest, mockResponse);

        verify(mockResponse, times(1)).sendRedirect("pages/register.jsp?error=1&msg=Registration+Failed");
    }

    @Test
    void testDoPost_MissingFields() throws ServletException, IOException {
        when(mockRequest.getParameter("first-name")).thenReturn("");
        when(mockRequest.getParameter("last-name")).thenReturn("Doe");
        when(mockRequest.getParameter("username")).thenReturn("johndoe");
        when(mockRequest.getParameter("email")).thenReturn("johndoe@example.com");
        when(mockRequest.getParameter("mobile")).thenReturn("1234567890");
        when(mockRequest.getParameter("password")).thenReturn("password123");

        controller.doPost(mockRequest, mockResponse);

        verify(mockResponse, times(1)).sendRedirect("pages/register.jsp?error=1&msg=Missing+Fields");
    }

    @Test
    void testDoGet_RendersRegistrationPage() throws ServletException, IOException {
        when(mockRequest.getRequestDispatcher("/pages/register.jsp")).thenReturn(mockDispatcher);

        controller.doGet(mockRequest, mockResponse);

        verify(mockDispatcher, times(1)).forward(mockRequest, mockResponse);
    }
}


