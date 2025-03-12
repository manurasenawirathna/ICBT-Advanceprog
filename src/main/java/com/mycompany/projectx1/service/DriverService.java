/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.service;

import com.mycompany.projectx1.dao.DriverDAO;
import com.mycompany.projectx1.model.Driver;

public class DriverService {
    private final DriverDAO driverDAO;

    // ✅ Constructor for Dependency Injection (for testing)
    public DriverService(DriverDAO driverDAO) {
        this.driverDAO = driverDAO;
    }

    // ✅ Default constructor for normal use
    public DriverService() {
        this.driverDAO = new DriverDAO();
    }

    public Driver getDriverByVehicleType(String selectedVehicle) {
        return driverDAO.getDriverByVehicleType(selectedVehicle);
    }
}


