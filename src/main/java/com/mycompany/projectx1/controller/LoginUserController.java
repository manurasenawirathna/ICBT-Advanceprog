/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.projectx1.controller;

import com.mycompany.projectx1.service.UserService;
import com.mycompany.projectx1.model.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginUserController extends HttpServlet {
    private UserService userService;

    // ✅ Default constructor
    public LoginUserController() {
        this.userService = new UserService();
    }

    // ✅ Constructor for testing
    public LoginUserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // ✅ Check if user exists in the database
        User user = userService.getUserByEmailAndPassword(email, password);

        if (user != null) {
            System.out.println("✅ Login SUCCESS: " + user.getUsername());
            response.sendRedirect("pages/login.jsp?success=1");  
        } else {
            System.out.println("❌ ERROR: Invalid email or password!");
            response.sendRedirect("pages/login.jsp?error=1");  
        }
    }
}



