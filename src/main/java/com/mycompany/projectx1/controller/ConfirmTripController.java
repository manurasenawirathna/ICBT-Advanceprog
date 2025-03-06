/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.controller;

import com.mycompany.projectx1.service.ConfirmedTripService;
import com.mycompany.projectx1.model.ConfirmedTrip;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/confirmTrip")
public class ConfirmTripController extends HttpServlet {
    private ConfirmedTripService confirmedTripService = new ConfirmedTripService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ✅ Retrieve data from session
        String tripId = request.getParameter("tripId");
        String passengerName = request.getParameter("passengerName");
        String pickupLocation = request.getParameter("pickupLocation");
        String dropLocation = request.getParameter("dropLocation");
        String selectedVehicle = request.getParameter("selectedVehicle");
        String totalDistanceStr = request.getParameter("totalDistance");
        String estimatedFareStr = request.getParameter("estimatedFare");

        double totalDistance = Double.parseDouble(totalDistanceStr);
        double estimatedFare = Double.parseDouble(estimatedFareStr);

        // ✅ Debugging: Print received form data in NetBeans Console
        System.out.println("📢 TRIP CONFIRMATION RECEIVED:");
        System.out.println("Trip ID: " + tripId);
        System.out.println("Passenger Name: " + passengerName);
        System.out.println("Pickup Location: " + pickupLocation);
        System.out.println("Drop Location: " + dropLocation);
        System.out.println("Selected Vehicle: " + selectedVehicle);
        System.out.println("Total Distance: " + totalDistance);
        System.out.println("Estimated Fare: " + estimatedFare);

        // ✅ Create an object and save to DB
        ConfirmedTrip trip = new ConfirmedTrip(tripId, passengerName, pickupLocation, dropLocation, selectedVehicle, totalDistance, estimatedFare);
        boolean isConfirmed = confirmedTripService.saveConfirmedTrip(trip);

        if (isConfirmed) {
            System.out.println("✅ Trip successfully saved in DB!");
            response.sendRedirect("pages/bookingconfirm.jsp?success=1");
        } else {
            System.out.println("❌ ERROR: Trip not saved in DB!");
            response.sendRedirect("pages/bookingconfirm.jsp?error=1");
        }
    }
}

