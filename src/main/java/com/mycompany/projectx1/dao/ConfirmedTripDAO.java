package com.mycompany.projectx1.dao;

import com.mycompany.projectx1.model.ConfirmedTrip;
import com.mycompany.projectx1.model.Driver;
import java.sql.*;

public class ConfirmedTripDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/projectx1_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Log@12345";

    public boolean insertConfirmedTrip(ConfirmedTrip trip) {
        String sql = "INSERT INTO confirmed_trips (trip_id, passenger_name, pickup_location, drop_location, selected_vehicle, total_distance, estimated_fare, driver_name, contact_number, vehicle_model, vehicle_color, vehicle_number, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, trip.getTripId());
            stmt.setString(2, trip.getPassengerName());
            stmt.setString(3, trip.getPickupLocation());
            stmt.setString(4, trip.getDropLocation());
            stmt.setString(5, trip.getSelectedVehicle());
            stmt.setDouble(6, trip.getTotalDistance());
            stmt.setDouble(7, trip.getEstimatedFare());
            stmt.setString(8, trip.getDriverName());
            stmt.setString(9, trip.getContactNumber());
            stmt.setString(10, trip.getVehicleModel());
            stmt.setString(11, trip.getVehicleColor());
            stmt.setString(12, trip.getVehicleNumber());
            stmt.setString(13, "Pending"); // Default status

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ✅ Add the missing `updateTripWithDriver` method
    public boolean updateTripWithDriver(String tripId, Driver driver, String status) {
        String sql = "UPDATE confirmed_trips SET driver_name = ?, contact_number = ?, vehicle_model = ?, vehicle_color = ?, vehicle_number = ?, status = ? WHERE trip_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, driver.getDriverName());
            stmt.setString(2, driver.getContactNumber());
            stmt.setString(3, driver.getVehicleModel());
            stmt.setString(4, driver.getVehicleColor());
            stmt.setString(5, driver.getVehicleNumber());
            stmt.setString(6, status);
            stmt.setString(7, tripId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
