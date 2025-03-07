/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.dao;

import com.mycompany.projectx1.model.Driver;
import java.sql.*;

public class DriverDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/projectx1_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "Log@12345";

    public Driver findDriverByVehicleType(String vehicleType) {
        String sql = "SELECT * FROM driver_base WHERE vehicle_type = ? ORDER BY RAND() LIMIT 1";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, vehicleType);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    return new Driver(
                        rs.getInt("driver_id"),
                        rs.getString("driver_name"),
                        rs.getString("contact_number"),
                        rs.getString("vehicle_model"),
                        rs.getString("vehicle_color"),
                        rs.getString("vehicle_number"),
                        rs.getString("vehicle_type")
                    );
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
