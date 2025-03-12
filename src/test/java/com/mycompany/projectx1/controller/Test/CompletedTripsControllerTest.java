/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.controller.Test;

import com.mycompany.projectx1.controller.CompletedTripsController;
import com.mycompany.projectx1.model.ConfirmedTrip;
import com.mycompany.projectx1.service.CompletedTripsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class CompletedTripsControllerTest {
    private CompletedTripsController controller;

    @Mock
    private CompletedTripsService mockTripService;
    @Mock
    private HttpServletRequest mockRequest;
    @Mock
    private HttpServletResponse mockResponse;
    @Mock
    private HttpSession mockSession;
    @Mock
    private RequestDispatcher mockDispatcher;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new CompletedTripsController(mockTripService); // ✅ Inject mock service
    }

    @Test
    void testDoGet_SuccessfulRetrieval() throws ServletException, IOException {
        // ✅ Mock request & session
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockRequest.getRequestDispatcher("pages/completedTrip.jsp")).thenReturn(mockDispatcher);

        // ✅ Mock trip service response
        List<ConfirmedTrip> mockTrips = Arrays.asList(
                new ConfirmedTrip("TRIP001", "Alice", "LocA", "LocB", "Sedan", 10.5, 25.75, "John Driver", "123-456-7890", "Toyota", "Blue", "ABC-123"),
                new ConfirmedTrip("TRIP002", "Bob", "LocX", "LocY", "SUV", 15.0, 40.00, "Mark Driver", "987-654-3210", "Nissan", "Black", "XYZ-789")
        );

        when(mockTripService.getCompletedTrips()).thenReturn(mockTrips);

        // ✅ Call the method
        controller.doGet(mockRequest, mockResponse);

        // ✅ Verify session attribute was set
        verify(mockSession, times(1)).setAttribute("completedTrips", mockTrips);

        // ✅ Verify forward to JSP page
        verify(mockDispatcher, times(1)).forward(mockRequest, mockResponse);
    }

    @Test
    void testDoGet_EmptyTripsList() throws ServletException, IOException {
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockRequest.getRequestDispatcher("pages/completedTrip.jsp")).thenReturn(mockDispatcher);

        when(mockTripService.getCompletedTrips()).thenReturn(Arrays.asList());

        controller.doGet(mockRequest, mockResponse);

        verify(mockSession, times(1)).setAttribute("completedTrips", Arrays.asList());
        verify(mockDispatcher, times(1)).forward(mockRequest, mockResponse);
    }
}

