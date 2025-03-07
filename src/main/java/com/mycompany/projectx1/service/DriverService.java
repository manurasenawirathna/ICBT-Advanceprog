/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.service;

import com.mycompany.projectx1.dao.DriverDAO;
import com.mycompany.projectx1.model.Driver;

public class DriverService {
    private DriverDAO driverDAO = new DriverDAO();

    public Driver getDriverByVehicleType(String vehicleType) {
        return driverDAO.findDriverByVehicleType(vehicleType);
    }
}
