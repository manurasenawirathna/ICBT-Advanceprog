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
    private String driverName;
    private String contactNumber;
    private String vehicleModel;
    private String vehicleColor;
    private String vehicleNumber;
    private Timestamp createdAt;

    // Constructor
    public ConfirmedTrip(String tripId, String passengerName, String pickupLocation, String dropLocation, String selectedVehicle, double totalDistance, double estimatedFare, String driverName, String contactNumber, String vehicleModel, String vehicleColor, String vehicleNumber) {
        this.tripId = tripId;
        this.passengerName = passengerName;
        this.pickupLocation = pickupLocation;
        this.dropLocation = dropLocation;
        this.selectedVehicle = selectedVehicle;
        this.totalDistance = totalDistance;
        this.estimatedFare = estimatedFare;
        this.driverName = driverName;
        this.contactNumber = contactNumber;
        this.vehicleModel = vehicleModel;
        this.vehicleColor = vehicleColor;
        this.vehicleNumber = vehicleNumber;
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

    public String getDriverName() { return driverName; }
    public void setDriverName(String driverName) { this.driverName = driverName; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    public String getVehicleModel() { return vehicleModel; }
    public void setVehicleModel(String vehicleModel) { this.vehicleModel = vehicleModel; }

    public String getVehicleColor() { return vehicleColor; }
    public void setVehicleColor(String vehicleColor) { this.vehicleColor = vehicleColor; }

    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
