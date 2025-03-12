/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.controller;

import com.mycompany.projectx1.model.Driver;
import com.mycompany.projectx1.service.DriverService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/confirm-booking")
public class DriverController extends HttpServlet {
    private DriverService driverService;

    // ✅ Default constructor
    public DriverController() {
        this.driverService = new DriverService();
    }

    // ✅ Constructor for testing
    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String vehicleType = request.getParameter("vehicle_type");

        if (vehicleType == null || vehicleType.isEmpty()) {
            response.sendRedirect("error.jsp?error=No vehicle type selected");
            return;
        }

        Driver driver = driverService.getDriverByVehicleType(vehicleType);

        if (driver != null) {
            request.getSession().setAttribute("driver", driver);
            response.sendRedirect("bookingconfirm.jsp");  
        } else {
            response.sendRedirect("error.jsp?error=No driver found for selected vehicle type");
        }
    }
}


