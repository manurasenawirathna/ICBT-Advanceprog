/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.controller.Test;

import com.mycompany.projectx1.controller.ConfirmTripController;
import com.mycompany.projectx1.model.ConfirmedTrip;
import com.mycompany.projectx1.model.Driver;
import com.mycompany.projectx1.service.ConfirmedTripService;
import com.mycompany.projectx1.service.DriverService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

class ConfirmTripControllerTest {
    private ConfirmTripController controller;

    @Mock
    private ConfirmedTripService mockConfirmedTripService;
    @Mock
    private DriverService mockDriverService;
    @Mock
    private HttpServletRequest mockRequest;
    @Mock
    private HttpServletResponse mockResponse;
    @Mock
    private HttpSession mockSession;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new ConfirmTripController(mockConfirmedTripService, mockDriverService); // ✅ Inject mock services
    }

    @Test
    void testDoPost_SuccessfulTripConfirmation() throws ServletException, IOException {
        // ✅ Mock request parameters
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockRequest.getParameter("tripId")).thenReturn("TRIP123");
        when(mockRequest.getParameter("passengerName")).thenReturn("John Doe");
        when(mockRequest.getParameter("pickupLocation")).thenReturn("Location A");
        when(mockRequest.getParameter("dropLocation")).thenReturn("Location B");
        when(mockRequest.getParameter("selectedVehicle")).thenReturn("Sedan");
        when(mockRequest.getParameter("totalDistance")).thenReturn("10.5");
        when(mockRequest.getParameter("estimatedFare")).thenReturn("25.75");

        // ✅ Mock driver service response
        Driver mockDriver = new Driver("John Driver", "123-456-7890", "Toyota", "Blue", "ABC-123", "Sedan");
        when(mockDriverService.getDriverByVehicleType("Sedan")).thenReturn(mockDriver);

        // ✅ Mock trip service response
        when(mockConfirmedTripService.saveConfirmedTrip(any(ConfirmedTrip.class))).thenReturn(true);

        // ✅ Call the method
        controller.doPost(mockRequest, mockResponse);

        // ✅ Verify session attribute was set
        verify(mockSession, times(1)).setAttribute("driver", mockDriver);

        // ✅ Verify redirect to booking confirmation page
        verify(mockResponse, times(1)).sendRedirect("pages/bookingconfirm.jsp?tripId=TRIP123");
    }

    @Test
    void testDoPost_MissingFormData() throws ServletException, IOException {
        when(mockRequest.getParameter("pickupLocation")).thenReturn(null);
        
        controller.doPost(mockRequest, mockResponse);

        verify(mockResponse, times(1)).sendRedirect("pages/tripdetailreview.jsp?error=1");
    }

    @Test
    void testDoPost_NoDriverAvailable() throws ServletException, IOException {
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockRequest.getParameter("tripId")).thenReturn("TRIP456");
        when(mockRequest.getParameter("passengerName")).thenReturn("Jane Doe");
        when(mockRequest.getParameter("pickupLocation")).thenReturn("Location X");
        when(mockRequest.getParameter("dropLocation")).thenReturn("Location Y");
        when(mockRequest.getParameter("selectedVehicle")).thenReturn("SUV");
        when(mockRequest.getParameter("totalDistance")).thenReturn("20.0");
        when(mockRequest.getParameter("estimatedFare")).thenReturn("50.0");

        // ✅ No driver found
        when(mockDriverService.getDriverByVehicleType("SUV")).thenReturn(null);
        when(mockConfirmedTripService.saveConfirmedTrip(any(ConfirmedTrip.class))).thenReturn(true);

        controller.doPost(mockRequest, mockResponse);

        // ✅ Ensure fallback driver was assigned
        verify(mockSession, times(1)).setAttribute(eq("driver"), any(Driver.class));

        verify(mockResponse, times(1)).sendRedirect("pages/bookingconfirm.jsp?tripId=TRIP456");
    }

    @Test
    void testDoPost_FailedTripConfirmation() throws ServletException, IOException {
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockRequest.getParameter("tripId")).thenReturn("TRIP789");
        when(mockRequest.getParameter("passengerName")).thenReturn("Alice");
        when(mockRequest.getParameter("pickupLocation")).thenReturn("Loc1");
        when(mockRequest.getParameter("dropLocation")).thenReturn("Loc2");
        when(mockRequest.getParameter("selectedVehicle")).thenReturn("Van");
        when(mockRequest.getParameter("totalDistance")).thenReturn("15.5");
        when(mockRequest.getParameter("estimatedFare")).thenReturn("40.0");

        // ✅ Mock driver service response
        Driver mockDriver = new Driver("Mark Driver", "987-654-3210", "Nissan", "Black", "XYZ-789", "Van");
        when(mockDriverService.getDriverByVehicleType("Van")).thenReturn(mockDriver);

        // ❌ Simulate database failure
        when(mockConfirmedTripService.saveConfirmedTrip(any(ConfirmedTrip.class))).thenReturn(false);

        controller.doPost(mockRequest, mockResponse);

        verify(mockResponse, times(1)).sendRedirect("pages/tripdetailreview.jsp?error=1");
    }
}

