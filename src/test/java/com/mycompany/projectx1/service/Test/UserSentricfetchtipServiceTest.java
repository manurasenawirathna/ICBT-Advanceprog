/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.service.Test;

import com.mycompany.projectx1.dao.UserSentricfetchtipDAO;
import com.mycompany.projectx1.model.ConfirmedTrip;
import com.mycompany.projectx1.service.UserSentricfetchtipService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserSentricfetchtipServiceTest {
    private UserSentricfetchtipService tripService;
    private UserSentricfetchtipDAO mockTripDAO;

    @BeforeEach
    void setUp() {
        // Mock the DAO
        mockTripDAO = mock(UserSentricfetchtipDAO.class);
        
        // Inject mock DAO into service
        tripService = new UserSentricfetchtipService(mockTripDAO);
    }

    @Test
    void testGetConfirmedTrips() {
        // Given
        List<ConfirmedTrip> mockTrips = Arrays.asList(
            new ConfirmedTrip("T1", "Driver1", "Passenger1"),
            new ConfirmedTrip("T2", "Driver2", "Passenger2")
        );

        // Simulating tripDAO returning mock data
        when(mockTripDAO.getConfirmedTrips()).thenReturn(mockTrips);

        // When
        List<ConfirmedTrip> result = tripService.getConfirmedTrips();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("T1", result.get(0).getTripId());
        verify(mockTripDAO, times(1)).getConfirmedTrips();
    }

    @Test
    void testCompleteTrip_Success() {
        // Given
        String tripId = "T1";

        // Simulating successful trip completion
        when(mockTripDAO.completeTrip(tripId)).thenReturn(true);

        // When
        boolean result = tripService.completeTrip(tripId);

        // Then
        assertTrue(result);
        verify(mockTripDAO, times(1)).completeTrip(tripId);
    }

    @Test
    void testCompleteTrip_Failure() {
        // Given
        String tripId = "InvalidTrip";

        // Simulating trip completion failure
        when(mockTripDAO.completeTrip(tripId)).thenReturn(false);

        // When
        boolean result = tripService.completeTrip(tripId);

        // Then
        assertFalse(result);
        verify(mockTripDAO, times(1)).completeTrip(tripId);
    }
}

