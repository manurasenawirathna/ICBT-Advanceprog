/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.controller;

import com.mycompany.projectx1.service.ConfirmedTripService;
import com.mycompany.projectx1.model.ConfirmedTrip;
import com.mycompany.projectx1.model.Driver;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bookingConfirm")
public class BookingConfirmController extends HttpServlet {
    private ConfirmedTripService confirmedTripService = new ConfirmedTripService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String tripId = request.getParameter("tripId");
        String action = request.getParameter("action"); // "done" or "cancel"

        Driver driver = (Driver) session.getAttribute("driver");

        if (driver == null) {
            System.out.println("❌ ERROR: No driver details found in session.");
            response.sendRedirect("pages/bookingconfirm.jsp?error=nodriver");
            return;
        }

        boolean isUpdated;
        if ("done".equals(action)) {
            isUpdated = confirmedTripService.updateTripWithDriver(tripId, driver, "Confirmed");
        } else if ("cancel".equals(action)) {
            isUpdated = confirmedTripService.updateTripWithDriver(tripId, driver, "Cancelled");
        } else {
            response.sendRedirect("pages/bookingconfirm.jsp?error=invalidAction");
            return;
        }

        if (isUpdated) {
            System.out.println("✅ Trip updated successfully!");
            response.sendRedirect("pages/bookingconfirm.jsp?success=1");
        } else {
            System.out.println("❌ ERROR: Failed to update trip.");
            response.sendRedirect("pages/bookingconfirm.jsp?error=updateFailed");
        }
    }
}
