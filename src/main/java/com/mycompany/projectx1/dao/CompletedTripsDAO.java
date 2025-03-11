/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.dao;

import com.mycompany.projectx1.model.ConfirmedTrip;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompletedTripsDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/projectx1_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Log@12345";

    // ✅ Fetch only trips with status "Completed"
    public List<ConfirmedTrip> getCompletedTrips() {
        List<ConfirmedTrip> trips = new ArrayList<>();
        String sql = "SELECT * FROM confirmed_trips WHERE BINARY TRIM(status) = 'Completed'";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                trips.add(new ConfirmedTrip(
                        rs.getString("trip_id"),
                        rs.getString("passenger_name"),
                        rs.getString("pickup_location"),
                        rs.getString("drop_location"),
                        rs.getString("selected_vehicle"),
                        rs.getDouble("total_distance"),
                        rs.getDouble("estimated_fare"),
                        rs.getString("driver_name"),
                        rs.getString("contact_number"),
                        rs.getString("vehicle_model"),
                        rs.getString("vehicle_color"),
                        rs.getString("vehicle_number")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trips;
    }
}

