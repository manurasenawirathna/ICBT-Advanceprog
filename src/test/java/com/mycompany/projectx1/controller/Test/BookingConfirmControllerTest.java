package com.mycompany.projectx1.controller.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.mycompany.projectx1.controller.BookingConfirmController;
import com.mycompany.projectx1.model.Driver;
import com.mycompany.projectx1.service.ConfirmedTripService;
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

class BookingConfirmControllerTest {

    private BookingConfirmController controller;
    
    @Mock
    private ConfirmedTripService mockConfirmedTripService;
    
    @Mock
    private HttpServletRequest mockRequest;
    
    @Mock
    private HttpServletResponse mockResponse;
    
    @Mock
    private HttpSession mockSession;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new BookingConfirmController();
    }

    @Test
    void testDoPost_InvalidAction() throws ServletException, IOException {
        when(mockRequest.getSession()).thenReturn(mockSession);
        when(mockRequest.getParameter("tripId")).thenReturn("TRIP123");
        when(mockRequest.getParameter("action")).thenReturn("invalidAction");

        // ✅ Ensure driver is set in the session
        Driver mockDriver = new Driver("John Doe", "123-456-7890", "Toyota", "Blue", "ABC-123", "Sedan");
        when(mockSession.getAttribute("driver")).thenReturn(mockDriver);

        controller.doPost(mockRequest, mockResponse);

        // ✅ Expect redirect to invalid action error page
        verify(mockResponse).sendRedirect("pages/bookingconfirm.jsp?error=invalidAction");
    }
}

