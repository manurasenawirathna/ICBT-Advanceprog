<%-- 
    Document   : register.jsp
    Created on : Feb 20, 2025, 5:45:35â¯PM
    Author     : manur
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register - ProjectX1</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/registerstyle.css">
    <script>
        window.onload = function() {
    const urlParams = new URLSearchParams(window.location.search);

    // Show alerts only if the form was submitted (check for the referrer)
    if (document.referrer.includes("register")) {
        if (urlParams.has('success')) {
            alert("â Registration successful!");
        }
        if (urlParams.has('error')) {
            alert("â Registration failed. Please try again.");
        }
    }
};

    </script>
</head>
<body>

    <div class="register-container">
        <h2>Create a New Account</h2>
        <form action="${pageContext.request.contextPath}/register" method="post">

            
            <div class="name-group">
                <div class="input-group half">
                    <label for="first-name">First Name:</label>
                    <input type="text" id="first-name" name="first-name" required>
                </div>
                <div class="input-group half">
                    <label for="last-name">Last Name:</label>
                    <input type="text" id="last-name" name="last-name" required>
                </div>
            </div>

            <div class="input-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>

            <div class="input-group">
                <label for="mobile">Mobile Number:</label>
                <div class="phone-container">
                    <img src="../videos/srilanka.png" alt="Sri Lanka" class="flag">
                    <span class="country-code">+94</span>
                    <input type="tel" id="mobile" name="mobile" placeholder="Enter number" required>
                </div>
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
