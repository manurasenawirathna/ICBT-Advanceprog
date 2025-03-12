/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.service;

import com.mycompany.projectx1.dao.ConfirmedTripDAO;
import com.mycompany.projectx1.model.ConfirmedTrip;
import com.mycompany.projectx1.model.Driver;

public class ConfirmedTripService {
    private final ConfirmedTripDAO confirmedTripDAO;

    // ✅ Constructor for Dependency Injection (For Testing)
    public ConfirmedTripService(ConfirmedTripDAO confirmedTripDAO) {
        this.confirmedTripDAO = confirmedTripDAO;
    }

    // ✅ Default constructor for normal use
    public ConfirmedTripService() {
        this.confirmedTripDAO = new ConfirmedTripDAO();
    }

    public boolean saveConfirmedTrip(ConfirmedTrip trip) {
        return confirmedTripDAO.insertConfirmedTrip(trip);
    }

    public boolean updateTripWithDriver(String tripId, Driver driver, String status) {
        return confirmedTripDAO.updateTripWithDriver(tripId, driver, status);
    }
}



