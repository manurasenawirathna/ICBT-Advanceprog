/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.controller.Test;

import com.mycompany.projectx1.controller.DriverController;
import com.mycompany.projectx1.model.Driver;
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

class DriverControllerTest {
    private DriverController controller;

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
        controller = new DriverController(mockDriverService); // ✅ Inject mock DriverService
    }

    @Test
    void testDoGet_SuccessfulDriverRetrieval() throws ServletException, IOException {
        // ✅ Mock request parameters
        when(mockRequest.getParameter("vehicle_type")).thenReturn("Sedan");

        // ✅ Mock session behavior
        when(mockRequest.getSession()).thenReturn(mockSession);

        // ✅ Mock service response
        Driver mockDriver = new Driver("John Doe", "123-456-7890", "Toyota", "Blue", "ABC-123", "Sedan");
        when(mockDriverService.getDriverByVehicleType("Sedan")).thenReturn(mockDriver);

        // ✅ Call the method
        controller.doGet(mockRequest, mockResponse);

        // ✅ Verify session attribute was set
        verify(mockSession, times(1)).setAttribute("driver", mockDriver);

        // ✅ Verify redirect to booking confirmation page
        verify(mockResponse, times(1)).sendRedirect("bookingconfirm.jsp");
    }

    @Test
    void testDoGet_NoVehicleTypeProvided() throws ServletException, IOException {
        when(mockRequest.getParameter("vehicle_type")).thenReturn(null);

        controller.doGet(mockRequest, mockResponse);

        verify(mockResponse, times(1)).sendRedirect("error.jsp?error=No vehicle type selected");
    }

    @Test
    void testDoGet_NoDriverFound() throws ServletException, IOException {
        when(mockRequest.getParameter("vehicle_type")).thenReturn("SUV");
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockDriverService.getDriverByVehicleType("SUV")).thenReturn(null);

        controller.doGet(mockRequest, mockResponse);

        verify(mockResponse, times(1)).sendRedirect("error.jsp?error=No driver found for selected vehicle type");
    }
}

