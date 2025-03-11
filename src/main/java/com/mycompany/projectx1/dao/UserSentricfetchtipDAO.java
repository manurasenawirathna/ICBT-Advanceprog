/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.dao;

import com.mycompany.projectx1.model.ConfirmedTrip;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserSentricfetchtipDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/projectx1_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Log@12345";

    // ✅ Fetch only trips with status "Confirmed"
    public List<ConfirmedTrip> getConfirmedTrips() {
        List<ConfirmedTrip> trips = new ArrayList<>();
        String sql = "SELECT * FROM confirmed_trips WHERE status = 'Confirmed'";

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

    // ✅ Update trip status to "Completed"
    public boolean completeTrip(String tripId) {
        String sql = "UPDATE confirmed_trips SET status = 'Completed' WHERE trip_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tripId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

