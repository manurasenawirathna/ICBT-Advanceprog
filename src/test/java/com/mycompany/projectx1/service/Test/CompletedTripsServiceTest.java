/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.service.Test;

import com.mycompany.projectx1.dao.CompletedTripsDAO;
import com.mycompany.projectx1.model.ConfirmedTrip;
import com.mycompany.projectx1.service.CompletedTripsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompletedTripsServiceTest {
    private CompletedTripsService completedTripsService;
    private CompletedTripsDAO mockCompletedTripsDAO;

    @BeforeEach
    void setUp() {
        // ✅ Mock the DAO
        mockCompletedTripsDAO = mock(CompletedTripsDAO.class);

        // ✅ Inject mock DAO into service
        completedTripsService = new CompletedTripsService(mockCompletedTripsDAO);
    }

    @Test
    void testGetCompletedTrips_Success() {
        // Given
        List<ConfirmedTrip> mockTrips = Arrays.asList(
            new ConfirmedTrip("T1", "Passenger1", "Destination1"),
            new ConfirmedTrip("T2", "Passenger2", "Destination2")
        );

        // Simulating DAO returning mock trips
        when(mockCompletedTripsDAO.getCompletedTrips()).thenReturn(mockTrips);

        // When
        List<ConfirmedTrip> result = completedTripsService.getCompletedTrips();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("T1", result.get(0).getTripId());
        verify(mockCompletedTripsDAO, times(1)).getCompletedTrips();
    }

    @Test
    void testGetCompletedTrips_EmptyList() {
        // Given
        when(mockCompletedTripsDAO.getCompletedTrips()).thenReturn(Arrays.asList());

        // When
        List<ConfirmedTrip> result = completedTripsService.getCompletedTrips();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(mockCompletedTripsDAO, times(1)).getCompletedTrips();
    }
}
