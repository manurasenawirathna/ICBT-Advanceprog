/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.service;

import com.mycompany.projectx1.dao.ConfirmedTripDAO;
import com.mycompany.projectx1.model.ConfirmedTrip;

public class ConfirmedTripService {
    private ConfirmedTripDAO confirmedTripDAO = new ConfirmedTripDAO();

    public boolean saveConfirmedTrip(String tripId, String passengerName, String pickupLocation, String dropLocation, String selectedVehicle, double totalDistance, double estimatedFare) {
        ConfirmedTrip trip = new ConfirmedTrip(tripId, passengerName, pickupLocation, dropLocation, selectedVehicle, totalDistance, estimatedFare);
        return confirmedTripDAO.insertConfirmedTrip(trip);
    }

    public boolean saveConfirmedTrip(ConfirmedTrip trip) {
        return confirmedTripDAO.insertConfirmedTrip(trip);
    }
}

