/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.service;

import com.mycompany.projectx1.dao.UserDAO;
import com.mycompany.projectx1.model.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public boolean registerUser(User user) {
        return userDAO.insertUser(user);
    }
}



