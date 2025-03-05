<%-- 
    Document   : login.jsp
    Created on : Feb 20, 2025, 5:25:05 PM
    Author     : manur
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - ProjectX1</title>
    <link rel="stylesheet" type="text/css" href="../css/loginstyle.css">
    <script src="https://cdn.lordicon.com/lordicon.js"></script>

    <script>
        window.onload = function() {
            const urlParams = new URLSearchParams(window.location.search);
            const successIcon = document.getElementById("success-icon");
            const formContainer = document.getElementById("form-container");

            if (document.referrer.includes("login")) {
                if (urlParams.has('success')) {
                    // Hide form and show animation
                    formContainer.style.display = "none";
                    successIcon.style.display = "block";

                    setTimeout(() => {
                        window.location.href = "booking.jsp"; // Redirect to booking.jsp
                    }, 5000); // 5-second delay before redirect
                }
                if (urlParams.has('error')) {
                    alert("❌ Invalid email or password. Please try again.");
                }
            }
        };
    </script>
</head>
<body>
    <div class="login-container">
        <h2>Login to Your Account</h2>
        
        <!-- Success Animation (Hidden by Default) -->
        <div id="success-icon" style="display: none;">
            <lord-icon
                src="https://cdn.lordicon.com/mfblariy.json"
                trigger="loop"
                state="loop-cycle"
                style="width:150px;height:150px">
            </lord-icon>
            <p style="color:white;">Redirecting...</p>
        </div>

        <!-- Login Form -->
        <div id="form-container">
            <form action="../login" method="post">
                <div class="input-group">
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" required>
                </div>
                <div class="input-group">
                    <label for="password">Password:</label>
                    <input type="password" id="password" name="password" required>
                </div>
                <button type="submit" class="login-btn">Login</button>
                <p class="register-link">Don't have an account? <a href="register.jsp">Sign up</a></p>
            </form>
        </div>
    </div>
</body>
</html>
