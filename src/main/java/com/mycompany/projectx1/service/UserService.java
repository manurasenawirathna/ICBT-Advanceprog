/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.service;

import com.mycompany.projectx1.dao.UserDAO;
import com.mycompany.projectx1.model.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public boolean registerUser(String firstName, String lastName, String username, String email, String phoneNumber, String password) {
        User user = new User(0, firstName, lastName, username, email, phoneNumber, password, null);
        return userDAO.insertUser(user);
    }

    // ✅ New method to call DAO for login verification
    public User getUserByEmailAndPassword(String email, String password) {
        return userDAO.getUserByEmailAndPassword(email, password);
    }
}





