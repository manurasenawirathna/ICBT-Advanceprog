/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.controller;

import com.mycompany.projectx1.service.UserSentricfetchtipService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/completeTrip")
public class UserSentricfetchtipController extends HttpServlet {
    private UserSentricfetchtipService tripService;

    // ✅ Default constructor for production
    public UserSentricfetchtipController() {
        this.tripService = new UserSentricfetchtipService();
    }

    // ✅ Constructor for testing (Injecting mock service)
    public UserSentricfetchtipController(UserSentricfetchtipService tripService) {
        this.tripService = tripService;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tripId = request.getParameter("tripId");

        if (tripId == null || tripId.isEmpty()) {
            response.sendRedirect("pages/pendingtrips.jsp?error=invalidTrip");
            return;
        }

        boolean isCompleted = tripService.completeTrip(tripId);

        if (isCompleted) {
            response.sendRedirect("pages/pendingtrips.jsp?success=completed");
        } else {
            response.sendRedirect("pages/pendingtrips.jsp?error=updateFailed");
        }
    }
}


