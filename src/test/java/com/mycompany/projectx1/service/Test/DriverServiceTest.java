/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.service;

import com.mycompany.projectx1.dao.DriverDAO;
import com.mycompany.projectx1.model.Driver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DriverServiceTest {
    private DriverService driverService;
    private DriverDAO mockDriverDAO;

    @BeforeEach
    void setUp() {
        // ✅ Mock the DAO
        mockDriverDAO = mock(DriverDAO.class);

        // ✅ Inject mock DAO into service
        driverService = new DriverService(mockDriverDAO);
    }

    @Test
    void testGetDriverByVehicleType_Found() {
        // Given
        String vehicleType = "Sedan";
        Driver mockDriver = new Driver("John Doe", "123-456-7890", "Toyota", "Blue", "ABC-123", vehicleType);


        // Simulating DAO returning a driver
        when(mockDriverDAO.getDriverByVehicleType(vehicleType)).thenReturn(mockDriver);

        // When
        Driver result = driverService.getDriverByVehicleType(vehicleType);

        // Then
        assertNotNull(result);
        assertEquals("John Doe", result.getDriverName()); // ✅ FIXED: Use getDriverName()
        assertEquals(vehicleType, result.getVehicleType());
        verify(mockDriverDAO, times(1)).getDriverByVehicleType(vehicleType);
    }

    @Test
    void testGetDriverByVehicleType_NotFound() {
        // Given
        String vehicleType = "Truck";

        // Simulating DAO returning null (no driver found)
        when(mockDriverDAO.getDriverByVehicleType(vehicleType)).thenReturn(null);

        // When
        Driver result = driverService.getDriverByVehicleType(vehicleType);

        // Then
        assertNull(result);
        verify(mockDriverDAO, times(1)).getDriverByVehicleType(vehicleType);
    }
}


