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
    private ConfirmedTripService confirmedTripService;

    // ✅ Default constructor
    public BookingConfirmController() {
        this.confirmedTripService = new ConfirmedTripService();
    }

    // ✅ Constructor for testing
    public BookingConfirmController(ConfirmedTripService confirmedTripService) {
        this.confirmedTripService = confirmedTripService;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String tripId = request.getParameter("tripId");
        String action = request.getParameter("action");

        Driver driver = (Driver) session.getAttribute("driver");

        if (driver == null) {
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
            if ("done".equals(action)) {
                response.sendRedirect("pages/pendingtrips.jsp");
            } else {
                response.sendRedirect("pages/booking.jsp");
            }
        } else {
            response.sendRedirect("pages/bookingconfirm.jsp?error=updateFailed");
        }
    }
}

