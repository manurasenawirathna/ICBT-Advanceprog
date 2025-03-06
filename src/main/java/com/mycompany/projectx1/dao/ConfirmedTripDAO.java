/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.dao;

import com.mycompany.projectx1.model.ConfirmedTrip;
import java.sql.*;

public class ConfirmedTripDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/projectx1_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root"; 
    private static final String PASSWORD = "Log@12345"; 

    public boolean insertConfirmedTrip(ConfirmedTrip trip) {
        String sql = "INSERT INTO confirmed_trips (trip_id, passenger_name, pickup_location, drop_location, selected_vehicle, total_distance, estimated_fare) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, trip.getTripId());
                stmt.setString(2, trip.getPassengerName());
                stmt.setString(3, trip.getPickupLocation());
                stmt.setString(4, trip.getDropLocation());
                stmt.setString(5, trip.getSelectedVehicle());
                stmt.setDouble(6, trip.getTotalDistance());
                stmt.setDouble(7, trip.getEstimatedFare());

                int rowsInserted = stmt.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

