/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.service;

import com.mycompany.projectx1.dao.UserSentricfetchtipDAO;
import com.mycompany.projectx1.model.ConfirmedTrip;
import java.util.List;

public class UserSentricfetchtipService {
    private UserSentricfetchtipDAO tripDAO = new UserSentricfetchtipDAO();

    public List<ConfirmedTrip> getConfirmedTrips() {
        return tripDAO.getConfirmedTrips();
    }

    public boolean completeTrip(String tripId) {
        return tripDAO.completeTrip(tripId);
    }
}

