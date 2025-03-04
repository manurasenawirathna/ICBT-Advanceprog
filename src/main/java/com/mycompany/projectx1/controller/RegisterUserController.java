package com.mycompany.projectx1.controller;

import com.mycompany.projectx1.service.UserService;
import com.mycompany.projectx1.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterUserController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Debugging - Print received form data
        System.out.println("Received Form Data:");
        System.out.println("First Name: " + request.getParameter("first-name"));
        System.out.println("Last Name: " + request.getParameter("last-name"));
        System.out.println("Username: " + request.getParameter("username"));
        System.out.println("Email: " + request.getParameter("email"));
        System.out.println("Phone Number: " + request.getParameter("mobile"));
        System.out.println("Password: " + request.getParameter("password"));

        // Create user object
        User user = new User(0, 
            request.getParameter("first-name"), 
            request.getParameter("last-name"), 
            request.getParameter("username"), 
            request.getParameter("email"), 
            request.getParameter("mobile"), 
            request.getParameter("password"), 
            null
        );

        // Save user in the database
        boolean isRegistered = userService.registerUser(user);

        if (isRegistered) {
            System.out.println("✅ Registration SUCCESSFUL!");
            response.sendRedirect("pages/register.jsp?success=1"); // Redirect with success message
        } else {
            System.out.println("❌ Registration FAILED!");
            response.sendRedirect("pages/register.jsp?error=1&msg=Registration%20failed.");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
    }
}
