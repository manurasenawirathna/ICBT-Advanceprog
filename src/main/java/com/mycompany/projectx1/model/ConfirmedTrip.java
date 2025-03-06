/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.model;

import java.sql.Timestamp;

public class ConfirmedTrip {
    private String tripId;
    private String passengerName;
    private String pickupLocation;
    private String dropLocation;
    private String selectedVehicle;
    private double totalDistance;
    private double estimatedFare;
    private Timestamp createdAt;

    // Constructor
    public ConfirmedTrip(String tripId, String passengerName, String pickupLocation, String dropLocation, String selectedVehicle, double totalDistance, double estimatedFare) {
        this.tripId = tripId;
        this.passengerName = passengerName;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.selectedVehicle = selectedVehicle;
        this.totalDistance = totalDistance;
        this.estimatedFare = estimatedFare;
    }

    // Getters and Setters
    public String getTripId() { return tripId; }
    public void setTripId(String tripId) { this.tripId = tripId; }

    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }

    public String getPickupLocation() { return pickupLocation; }
    public void setPickupLocation(String pickupLocation) { this.pickupLocation = pickupLocation; }

    public String getDropLocation() { return dropLocation; }
    public void setDropLocation(String dropLocation) { this.dropLocation = dropLocation; }

    public String getSelectedVehicle() { return selectedVehicle; }
    public void setSelectedVehicle(String selectedVehicle) { this.selectedVehicle = selectedVehicle; }

    public double getTotalDistance() { return totalDistance; }
    public void setTotalDistance(double totalDistance) { this.totalDistance = totalDistance; }

    public double getEstimatedFare() { return estimatedFare; }
    public void setEstimatedFare(double estimatedFare) { this.estimatedFare = estimatedFare; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}

