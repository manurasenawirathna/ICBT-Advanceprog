<%-- 
    Document   : register.jsp
    Created on : Feb 20, 2025, 5:45:35 PM
    Author     : manur
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - ProjectX1</title>
    <link rel="stylesheet" type="text/css" href="../css/registerstyle.css">
</head>
<body>
    <div class="register-container">
        <h2>Create a New Account</h2>
        <form action="../register" method="post">
            <div class="input-group">
                <label for="name">Full Name:</label>
                <input type="text" id="name" name="name" required>
            </div>
            <div class="input-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>
            </div>
            <div class="input-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <div class="input-group">
                <label for="confirm-password">Confirm Password:</label>
                <input type="password" id="confirm-password" name="confirm-password" required>
            </div>
            <button type="submit" class="register-btn">Register</button>
            <p class="login-link">Already have an account? <a href="login.jsp">Login here</a></p>
        </form>
    </div>
</body>
</html>

