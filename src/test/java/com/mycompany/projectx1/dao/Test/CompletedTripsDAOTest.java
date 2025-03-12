/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.dao.Test;

import com.mycompany.projectx1.dao.CompletedTripsDAO;
import com.mycompany.projectx1.model.ConfirmedTrip;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompletedTripsDAOTest {
    private CompletedTripsDAO completedTripsDAO;

    @Mock
    private Connection mockConnection;
    @Mock
    private Statement mockStatement;
    @Mock
    private ResultSet mockResultSet;

    private MockedStatic<DriverManager> mockedDriverManager; // ✅ Static Mock

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
        completedTripsDAO = new CompletedTripsDAO();

        // ✅ Mock DriverManager to return a mocked connection
        mockedDriverManager = mockStatic(DriverManager.class);
        mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                           .thenReturn(mockConnection);

        when(mockConnection.createStatement()).thenReturn(mockStatement);
    }

    @AfterEach
    void tearDown() {
        mockedDriverManager.close(); // ✅ Release the static mock
    }

    @Test
    void testGetCompletedTrips_Success() throws Exception {
        // Given
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, true, false); // Two trips found

        when(mockResultSet.getString("trip_id")).thenReturn("T1", "T2");
        when(mockResultSet.getString("passenger_name")).thenReturn("Passenger1", "Passenger2");
        when(mockResultSet.getString("pickup_location")).thenReturn("Pickup1", "Pickup2");
        when(mockResultSet.getString("drop_location")).thenReturn("Drop1", "Drop2");
        when(mockResultSet.getString("selected_vehicle")).thenReturn("Sedan", "SUV");
        when(mockResultSet.getDouble("total_distance")).thenReturn(10.5, 20.0);
        when(mockResultSet.getDouble("estimated_fare")).thenReturn(100.0, 200.0);
        when(mockResultSet.getString("driver_name")).thenReturn("Driver1", "Driver2");
        when(mockResultSet.getString("contact_number")).thenReturn("1234567890", "0987654321");
        when(mockResultSet.getString("vehicle_model")).thenReturn("Toyota", "Honda");
        when(mockResultSet.getString("vehicle_color")).thenReturn("Blue", "Black");
        when(mockResultSet.getString("vehicle_number")).thenReturn("ABC-123", "XYZ-789");

        // When
        List<ConfirmedTrip> result = completedTripsDAO.getCompletedTrips();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("T1", result.get(0).getTripId());
        assertEquals("Passenger2", result.get(1).getPassengerName());
        verify(mockStatement, times(1)).executeQuery(anyString());
    }

    @Test
    void testGetCompletedTrips_EmptyList() throws Exception {
        // Given
        when(mockStatement.executeQuery(anyString())).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(false); // No trips found

        // When
        List<ConfirmedTrip> result = completedTripsDAO.getCompletedTrips();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(mockStatement, times(1)).executeQuery(anyString());
    }
}
