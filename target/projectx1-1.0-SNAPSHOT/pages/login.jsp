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
</head>
<body>
    <div class="login-container">
        <h2>Login to Your Account</h2>
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
</body>
</html>

