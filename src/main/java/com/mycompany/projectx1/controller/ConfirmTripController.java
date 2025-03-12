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
    private ConfirmedTripService confirmedTripService;
    private DriverService driverService;

    // ✅ Default constructor
    public ConfirmTripController() {
        this.confirmedTripService = new ConfirmedTripService();
        this.driverService = new DriverService();
    }

    // ✅ Constructor for testing
    public ConfirmTripController(ConfirmedTripService confirmedTripService, DriverService driverService) {
        this.confirmedTripService = confirmedTripService;
        this.driverService = driverService;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String tripId = request.getParameter("tripId");
        String passengerName = request.getParameter("passengerName");
        String pickupLocation = request.getParameter("pickupLocation");
        String dropLocation = request.getParameter("dropLocation");
        String selectedVehicle = request.getParameter("selectedVehicle");
        String totalDistanceStr = request.getParameter("totalDistance");
        String estimatedFareStr = request.getParameter("estimatedFare");

        if (pickupLocation == null || dropLocation == null || totalDistanceStr == null || estimatedFareStr == null || selectedVehicle == null) {
            response.sendRedirect("pages/tripdetailreview.jsp?error=1");
            return;
        }

        double totalDistance = Double.parseDouble(totalDistanceStr);
        double estimatedFare = Double.parseDouble(estimatedFareStr);

        Driver driver = driverService.getDriverByVehicleType(selectedVehicle);

        if (driver == null) {
            driver = new Driver("Not Assigned", "0000000000", "Unknown Model", "Unknown Color", "XXXXXX", selectedVehicle);
        }

        session.setAttribute("driver", driver);

        ConfirmedTrip trip = new ConfirmedTrip(tripId, passengerName, pickupLocation, dropLocation, selectedVehicle, totalDistance, estimatedFare, driver.getDriverName(), driver.getContactNumber(), driver.getVehicleModel(), driver.getVehicleColor(), driver.getVehicleNumber());

        boolean isConfirmed = confirmedTripService.saveConfirmedTrip(trip);

        if (isConfirmed) {
            response.sendRedirect("pages/bookingconfirm.jsp?tripId=" + tripId);
        } else {
            response.sendRedirect("pages/tripdetailreview.jsp?error=1");
        }
    }
}



