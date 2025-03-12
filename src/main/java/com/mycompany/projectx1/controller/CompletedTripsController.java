/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.controller;

import com.mycompany.projectx1.service.CompletedTripsService;
import com.mycompany.projectx1.model.ConfirmedTrip;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/completedTrips")
public class CompletedTripsController extends HttpServlet {
    private CompletedTripsService tripService;

    // ✅ Default constructor
    public CompletedTripsController() {
        this.tripService = new CompletedTripsService();
    }

    // ✅ Constructor for testing
    public CompletedTripsController(CompletedTripsService tripService) {
        this.tripService = tripService;
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        List<ConfirmedTrip> completedTrips = tripService.getCompletedTrips();

        session.setAttribute("completedTrips", completedTrips);
        request.getRequestDispatcher("pages/completedTrip.jsp").forward(request, response);
    }
}

