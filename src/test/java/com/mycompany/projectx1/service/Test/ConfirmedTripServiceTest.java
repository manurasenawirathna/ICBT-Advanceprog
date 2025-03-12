/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.service;

import com.mycompany.projectx1.dao.ConfirmedTripDAO;
import com.mycompany.projectx1.model.ConfirmedTrip;
import com.mycompany.projectx1.model.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConfirmedTripServiceTest {
    private ConfirmedTripService confirmedTripService;
    private ConfirmedTripDAO mockConfirmedTripDAO;

    @BeforeEach
    void setUp() {
        // ✅ Mock the DAO
        mockConfirmedTripDAO = mock(ConfirmedTripDAO.class);

        // ✅ Inject mock DAO into service
        confirmedTripService = new ConfirmedTripService(mockConfirmedTripDAO);
    }

    @Test
    void testSaveConfirmedTrip_Success() {
        // Given
        ConfirmedTrip mockTrip = new ConfirmedTrip("T1", "Passenger1", "Destination", "Some Location", "Some Date", 
                                           10.5, 15.0, "Status", "DriverName", "VehicleType", "PlateNumber", "Color");


        // Simulating successful insertion
        when(mockConfirmedTripDAO.insertConfirmedTrip(mockTrip)).thenReturn(true);

        // When
        boolean result = confirmedTripService.saveConfirmedTrip(mockTrip);

        // Then
        assertTrue(result);
        verify(mockConfirmedTripDAO, times(1)).insertConfirmedTrip(mockTrip);
    }

    @Test
    void testSaveConfirmedTrip_Failure() {
        // Given
        ConfirmedTrip mockTrip = new ConfirmedTrip("T1", "Passenger1", "Destination", "Some Location", "Some Date", 
                                           10.5, 15.0, "Status", "DriverName", "VehicleType", "PlateNumber", "Color");


        // Simulating insertion failure
        when(mockConfirmedTripDAO.insertConfirmedTrip(mockTrip)).thenReturn(false);

        // When
        boolean result = confirmedTripService.saveConfirmedTrip(mockTrip);

        // Then
        assertFalse(result);
        verify(mockConfirmedTripDAO, times(1)).insertConfirmedTrip(mockTrip);
    }

    @Test
    void testUpdateTripWithDriver_Success() {
        // Given
        String tripId = "T1";
        Driver mockDriver = new Driver("John Doe", "123-456-7890", "Toyota", "Blue", "ABC-123", "Sedan");

        String status = "Completed";

        // Simulating successful update
        when(mockConfirmedTripDAO.updateTripWithDriver(tripId, mockDriver, status)).thenReturn(true);

        // When
        boolean result = confirmedTripService.updateTripWithDriver(tripId, mockDriver, status);

        // Then
        assertTrue(result);
        verify(mockConfirmedTripDAO, times(1)).updateTripWithDriver(tripId, mockDriver, status);
    }

    @Test
    void testUpdateTripWithDriver_Failure() {
        // Given
        String tripId = "T1";
        Driver mockDriver = new Driver("John Doe", "123-456-7890", "Toyota", "Blue", "ABC-123", "Sedan");

        String status = "Completed";

        // Simulating update failure
        when(mockConfirmedTripDAO.updateTripWithDriver(tripId, mockDriver, status)).thenReturn(false);

        // When
        boolean result = confirmedTripService.updateTripWithDriver(tripId, mockDriver, status);

        // Then
        assertFalse(result);
        verify(mockConfirmedTripDAO, times(1)).updateTripWithDriver(tripId, mockDriver, status);
    }
}
