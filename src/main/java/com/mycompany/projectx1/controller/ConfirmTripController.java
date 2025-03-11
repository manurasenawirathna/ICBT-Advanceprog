/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.controller;

import com.mycompany.projectx1.service.ConfirmedTripService;
import com.mycompany.projectx1.service.DriverService;
import com.mycompany.projectx1.model.ConfirmedTrip;
import com.mycompany.projectx1.model.Driver;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/confirmTrip")
public class ConfirmTripController extends HttpServlet {
    private ConfirmedTripService confirmedTripService = new ConfirmedTripService();
    private DriverService driverService = new DriverService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // ✅ Retrieve Data from Form Submission
        String tripId = request.getParameter("tripId");
        String passengerName = request.getParameter("passengerName");
        String pickupLocation = request.getParameter("pickupLocation");
        String dropLocation = request.getParameter("dropLocation");
        String selectedVehicle = request.getParameter("selectedVehicle");
        String totalDistanceStr = request.getParameter("totalDistance");
        String estimatedFareStr = request.getParameter("estimatedFare");

        if (pickupLocation == null || dropLocation == null || totalDistanceStr == null || estimatedFareStr == null || selectedVehicle == null) {
            System.out.println("❌ ERROR: Missing form data!");
            response.sendRedirect("pages/tripdetailreview.jsp?error=1");
            return;
        }

        double totalDistance = Double.parseDouble(totalDistanceStr);
        double estimatedFare = Double.parseDouble(estimatedFareStr);

        // ✅ Assign a Driver Based on Vehicle Type
        Driver driver = driverService.getDriverByVehicleType(selectedVehicle);

        if (driver == null) {
            System.out.println("❌ ERROR: No driver available for " + selectedVehicle);
            driver = new Driver("Not Assigned", "0000000000", "Unknown Model", "Unknown Color", "XXXXXX"); // Placeholder driver
        }

        session.setAttribute("driver", driver);

        // ✅ Save Trip Details to the Database
        ConfirmedTrip trip = new ConfirmedTrip(tripId, passengerName, pickupLocation, dropLocation, selectedVehicle, totalDistance, estimatedFare, driver.getDriverName(), driver.getContactNumber(), driver.getVehicleModel(), driver.getVehicleColor(), driver.getVehicleNumber());

        boolean isConfirmed = confirmedTripService.saveConfirmedTrip(trip);

        if (isConfirmed) {
            System.out.println("✅ Trip successfully saved in DB!");
            response.sendRedirect("pages/bookingconfirm.jsp?success=1");
        } else {
            System.out.println("❌ ERROR: Trip not saved in DB!");
            response.sendRedirect("pages/tripdetailreview.jsp?error=1");
        }
    }
}


