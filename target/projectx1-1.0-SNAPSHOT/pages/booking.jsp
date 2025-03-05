<%-- 
    Document   : booking.jsp
    Created on : Feb 20, 2025, 6:07:17 PM
    Author     : manur
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Your Ride</title>
    <link rel="stylesheet" type="text/css" href="../css/bookingstyle.css">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script>
</head>
<body>

    <!-- Fullscreen Video Background -->
    <video autoplay loop muted playsinline id="bg-video">
        <source src="../videos/test889.mp4" type="video/mp4">
        Your browser does not support the video tag.
    </video>

    <!-- Booking Form -->
    <div class="booking-container">
        <h2>Good Day Manura!</h2>
        <h3>Enter Your Ride Details</h3>
        <form action="vehicle-selection.jsp" method="post" onsubmit="saveSessionData(event)">
            <div class="input-group">
                <label for="pickup"><i class="fas fa-map-marker-alt"></i> Pickup Address:</label>
                <input type="text" id="pickup" name="pickup" placeholder="Enter pickup location" required>
            </div>
            <div class="input-group">
                <label for="drop"><i class="fas fa-map-marker-alt"></i> Drop Location:</label>
                <input type="text" id="drop" name="drop" placeholder="Enter drop-off location" required>
            </div>
            <div class="input-group">
                <label for="distance"><i class="fas fa-road"></i> Total Kms to Destination:</label>
                <input type="number" id="distance" name="distance" placeholder="Enter total distance" required>
            </div>
            <button type="submit" class="next-btn">Next: Select Vehicle</button>
        </form>
    </div>

    <script>
        function saveSessionData(event) {
            event.preventDefault(); 
            
            let pickup = document.getElementById("pickup").value;
            let drop = document.getElementById("drop").value;
            let distance = document.getElementById("distance").value;

            if (pickup && drop && distance) {
                sessionStorage.setItem("pickup", pickup);
                sessionStorage.setItem("drop", drop);
                sessionStorage.setItem("distance", distance);
                
                window.location.href = "vehicle-selection.jsp";
            } else {
                alert("Please fill all the details before proceeding.");
            }
        }
    </script>
</body>
</html>
