package com.mycompany.projectx1.controller;

import com.mycompany.projectx1.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterUserController extends HttpServlet {
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // ✅ Retrieve form data
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("mobile");
        String password = request.getParameter("password");

        // ✅ Debugging: Print received form data in NetBeans Console
        System.out.println("📢 FORM DATA RECEIVED IN CONTROLLER:");
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Password: " + password);

        // ✅ Check if values are NULL or Empty
        if (firstName == null || lastName == null || username == null || email == null || phoneNumber == null || password == null ||
            firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || password.isEmpty()) {
            System.out.println("❌ ERROR: Missing or Empty form fields");
            response.sendRedirect("pages/register.jsp?error=1&msg=Missing+Fields");
            return;
        }

        // ✅ Save user to the database
        boolean isRegistered = userService.registerUser(firstName, lastName, username, email, phoneNumber, password);

        if (isRegistered) {
            System.out.println("✅ Registration SUCCESSFUL!");
            response.sendRedirect("pages/register.jsp?success=1&msg=Registration+Successful");
        } else {
            System.out.println("❌ Registration FAILED!");
            response.sendRedirect("pages/register.jsp?error=1&msg=Registration+Failed");
        }
    }

    // ✅ Handle GET requests so 405 error doesn’t occur
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
    }
}
