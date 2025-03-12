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
    private UserService userService;

    // ✅ Add constructor for dependency injection
    public RegisterUserController() {
        this.userService = new UserService();
    }

    public RegisterUserController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Change methods from `protected` to `public`
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("mobile");
        String password = request.getParameter("password");

        if (firstName == null || lastName == null || username == null || email == null || phoneNumber == null || password == null ||
                firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || password.isEmpty()) {
            response.sendRedirect("pages/register.jsp?error=1&msg=Missing+Fields");
            return;
        }

        boolean isRegistered = userService.registerUser(firstName, lastName, username, email, phoneNumber, password);

        if (isRegistered) {
            response.sendRedirect("pages/register.jsp?success=1&msg=Registration+Successful");
        } else {
            response.sendRedirect("pages/register.jsp?error=1&msg=Registration+Failed");
        }
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/pages/register.jsp").forward(request, response);
    }
}

