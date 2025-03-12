/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.dao.Test;

import com.mycompany.projectx1.dao.ConfirmedTripDAO;
import com.mycompany.projectx1.model.ConfirmedTrip;
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

class ConfirmedTripDAOTest {
    private ConfirmedTripDAO confirmedTripDAO;

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
        confirmedTripDAO = new ConfirmedTripDAO();

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
    void testInsertConfirmedTrip_Success() throws Exception {
        // Given
        ConfirmedTrip trip = new ConfirmedTrip("T1", "John Doe", "Location A", "Location B", "Sedan",
                10.5, 150.0, "Driver1", "1234567890", "Toyota", "Blue", "ABC-123");

        // Mock behavior
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // When
        boolean result = confirmedTripDAO.insertConfirmedTrip(trip);

        // Then
        assertTrue(result);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testInsertConfirmedTrip_Failure() throws Exception {
        // Given
        ConfirmedTrip trip = new ConfirmedTrip("T2", "Jane Doe", "Location X", "Location Y", "SUV",
                15.0, 200.0, "Driver2", "0987654321", "Honda", "Black", "XYZ-789");

        // Mock behavior
        when(mockPreparedStatement.executeUpdate()).thenReturn(0);

        // When
        boolean result = confirmedTripDAO.insertConfirmedTrip(trip);

        // Then
        assertFalse(result);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testUpdateTripWithDriver_Success() throws Exception {
        // Given
        String tripId = "T1";
        Driver driver = new Driver("Driver1", "1234567890", "Toyota", "Blue", "ABC-123", "Sedan");
        String status = "Completed";

        // Mock behavior
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);

        // When
        boolean result = confirmedTripDAO.updateTripWithDriver(tripId, driver, status);

        // Then
        assertTrue(result);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }

    @Test
    void testUpdateTripWithDriver_Failure() throws Exception {
        // Given
        String tripId = "T2";
        Driver driver = new Driver("Driver2", "0987654321", "Honda", "Black", "XYZ-789", "SUV");
        String status = "Completed";

        // Mock behavior
        when(mockPreparedStatement.executeUpdate()).thenReturn(0); // No update occurred

        // When
        boolean result = confirmedTripDAO.updateTripWithDriver(tripId, driver, status);

        // Then
        assertFalse(result);
        verify(mockPreparedStatement, times(1)).executeUpdate();
    }
}

