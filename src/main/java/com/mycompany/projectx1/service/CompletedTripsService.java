/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.service;

import com.mycompany.projectx1.dao.CompletedTripsDAO;
import com.mycompany.projectx1.model.ConfirmedTrip;
import java.util.List;

public class CompletedTripsService {
    private final CompletedTripsDAO tripDAO;

    // ✅ Constructor for Dependency Injection (For Testing)
    public CompletedTripsService(CompletedTripsDAO tripDAO) {
        this.tripDAO = tripDAO;
    }

    // ✅ Default Constructor for Normal Use
    public CompletedTripsService() {
        this.tripDAO = new CompletedTripsDAO();
    }

    public List<ConfirmedTrip> getCompletedTrips() {
        return tripDAO.getCompletedTrips();
    }
}


