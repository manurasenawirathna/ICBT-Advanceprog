/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.dao.Test;

import com.mycompany.projectx1.dao.DriverDAO;
import com.mycompany.projectx1.model.Driver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DriverDAOTest {
    private DriverDAO driverDAO;

    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;

    private MockedStatic<DriverManager> mockedDriverManager; // ✅ Static Mock

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        driverDAO = new DriverDAO();

        // ✅ Mock DriverManager to return a mocked connection
        mockedDriverManager = mockStatic(DriverManager.class);
        mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                           .thenReturn(mockConnection);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
    }

    @AfterEach
    void tearDown() {
        mockedDriverManager.close(); // ✅ Release the static mock
    }

    @Test
    void testGetDriverByVehicleType_Success() throws Exception {
        // Given
        String vehicleType = "Sedan";
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true); // A driver is found

        when(mockResultSet.getString("driver_name")).thenReturn("John Doe");
        when(mockResultSet.getString("contact_number")).thenReturn("123-456-7890");
        when(mockResultSet.getString("vehicle_model")).thenReturn("Toyota");
        when(mockResultSet.getString("vehicle_color")).thenReturn("Blue");
        when(mockResultSet.getString("vehicle_number")).thenReturn("ABC-123");
        when(mockResultSet.getString("vehicle_type")).thenReturn(vehicleType);

        // When
        Driver result = driverDAO.getDriverByVehicleType(vehicleType);

        // Then
        assertNotNull(result);
        assertEquals("John Doe", result.getDriverName());
        assertEquals("123-456-7890", result.getContactNumber());
        assertEquals("Toyota", result.getVehicleModel());
        assertEquals("Blue", result.getVehicleColor());
        assertEquals("ABC-123", result.getVehicleNumber());
        assertEquals("Sedan", result.getVehicleType());

        verify(mockPreparedStatement, times(1)).executeQuery();
    }

    @Test
    void testGetDriverByVehicleType_NotFound() throws Exception {
        // Given
        String vehicleType = "SUV";
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false); // No driver found

        // When
        Driver result = driverDAO.getDriverByVehicleType(vehicleType);

        // Then
        assertNull(result);
        verify(mockPreparedStatement, times(1)).executeQuery();
    }
}

