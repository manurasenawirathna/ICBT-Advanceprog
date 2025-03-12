/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.controller.Test;

import com.mycompany.projectx1.controller.UserSentricfetchtipController;
import com.mycompany.projectx1.service.UserSentricfetchtipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

class UserSentricfetchtipControllerTest {
    private UserSentricfetchtipController controller;

    @Mock
    private UserSentricfetchtipService mockTripService;
    @Mock
    private HttpServletRequest mockRequest;
    @Mock
    private HttpServletResponse mockResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockTripService = mock(UserSentricfetchtipService.class);
        controller = new UserSentricfetchtipController(mockTripService); // ✅ Inject mock service
    }

    @Test
    void testDoPost_TripCompleted_Success() throws ServletException, IOException {
        when(mockRequest.getParameter("tripId")).thenReturn("T123");
        when(mockTripService.completeTrip("T123")).thenReturn(true);

        controller.doPost(mockRequest, mockResponse);

        verify(mockTripService, times(1)).completeTrip("T123");
        verify(mockResponse, times(1)).sendRedirect("pages/pendingtrips.jsp?success=completed");
    }

    @Test
    void testDoPost_TripCompletion_Failure() throws ServletException, IOException {
        when(mockRequest.getParameter("tripId")).thenReturn("T123");
        when(mockTripService.completeTrip("T123")).thenReturn(false);

        controller.doPost(mockRequest, mockResponse);

        verify(mockTripService, times(1)).completeTrip("T123");
        verify(mockResponse, times(1)).sendRedirect("pages/pendingtrips.jsp?error=updateFailed");
    }

    @Test
    void testDoPost_MissingTripId() throws ServletException, IOException {
        when(mockRequest.getParameter("tripId")).thenReturn(null);

        controller.doPost(mockRequest, mockResponse);

        verify(mockTripService, never()).completeTrip(anyString()); // ✅ Should never call service
        verify(mockResponse, times(1)).sendRedirect("pages/pendingtrips.jsp?error=invalidTrip");
    }
}


