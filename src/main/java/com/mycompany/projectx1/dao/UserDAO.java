/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.dao;

import com.mycompany.projectx1.model.User;
import java.sql.*;

public class UserDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/projectx1_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root"; 
    private static final String PASSWORD = "Log@12345"; 

    public boolean insertUser(User user) {
        String sql = "INSERT INTO users (first_name, last_name, username, email, phone_number, password) VALUES (?, ?, ?, ?, ?, ?)";

        try {
            // ✅ Explicitly Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // ✅ Establish Connection
            try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, user.getFirstName());
                stmt.setString(2, user.getLastName());
                stmt.setString(3, user.getUsername());
                stmt.setString(4, user.getEmail());
                stmt.setString(5, user.getPhoneNumber());
                stmt.setString(6, user.getPassword());

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("✅ SUCCESS: User registered!");
                    return true;
                } else {
                    System.out.println("❌ ERROR: No rows inserted.");
                    return false;
                }
            }
        } catch (ClassNotFoundException e) {
            System.err.println("❌ ERROR: MySQL JDBC Driver not found!");
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            System.err.println("❌ SQL ERROR: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
