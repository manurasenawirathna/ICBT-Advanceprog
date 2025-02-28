<%-- 
    Document   : booking.jsp
    Created on : Feb 20, 2025, 6:07:17 PM
    Author     : manur
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Your Ride</title>
    <link rel="stylesheet" type="text/css" href="../css/bookingstyle.css">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script> <!-- For Icons -->
</head>
<body>
    <div class="booking-container">
        <h2>Enter Your Ride Details</h2>
        <form action="vehicle-selection.jsp" method="get">
            <div class="input-group">
                <label for="pickup"><i class="fas fa-map-marker-alt"></i> Pickup Address:</label>
                <input type="text" id="pickup" name="pickup" placeholder="Enter pickup location" required>
            </div>
            <div class="input-group">
                <label for="drop"><i class="fas fa-map-marker-alt"></i> Drop Location:</label>
                <input type="text" id="drop" name="drop" placeholder="Enter drop-off location" required>
            </div>
            <button type="submit" class="next-btn">Next: Select Vehicle</button>
        </form>
    </div>
</body>
</html>
