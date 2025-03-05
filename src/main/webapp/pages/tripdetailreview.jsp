<%-- 
    Document   : tripdetailreview.jsp
    Created on : Feb 20, 2025, 8:21:51 PM
    Author     : manur
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Trip Summary</title>

    <!-- Google Fonts (Poppins) -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="../css/tripdetailreview.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>

    <!-- Fullscreen Video Background -->
    <video autoplay loop muted playsinline id="bg-video">
        <source src="../videos/test889.mp4" type="video/mp4">
        Your browser does not support the video tag.
    </video>

    <!-- Trip Summary Container -->
    <div class="summary-container">
        <h2>Trip Summary</h2>
        
        <div class="trip-info">
            <p><b>Trip Code:</b> <span id="tripCode"></span></p>
            <p><b>Passenger Name:</b> <span id="passengerName"></span></p>
            <p><b>Pickup Location:</b> <span id="pickupLocation"></span></p>
            <p><b>Drop Location:</b> <span id="dropLocation"></span></p>
            <p><b>Selected Vehicle:</b> <span id="selectedVehicle"></span></p>
            <p><b>Total Distance:</b> <span id="totalDistance"></span></p>
            <p><b>Estimated Fare:</b> <span id="estimatedFare"></span></p>
        </div>

        <button id="confirm-btn" class="confirm-btn" onclick="confirmTrip()">Confirm & Proceed</button>

        <div id="loading-animation" class="loader" style="display: none;"></div>
        <p id="loading-text" style="display: none;">Processing your booking...</p>
    </div>

    <script>
        // Generate a random Trip Code
        function generateTripCode() {
            return "TRIP" + Math.floor(Math.random() * 900000 + 100000);
        }

        // Retrieve booking details from session storage and display them
        $(document).ready(function() {
            $("#tripCode").text(generateTripCode());
            $("#passengerName").text(sessionStorage.getItem("passengerName") || ""); // Keep Passenger Name Blank
            $("#pickupLocation").text(sessionStorage.getItem("pickup") || "Not Provided"); 
            $("#dropLocation").text(sessionStorage.getItem("drop") || "Not Provided"); 
            $("#totalDistance").text(sessionStorage.getItem("distance") ? sessionStorage.getItem("distance") + " km" : "Calculating...");
            $("#selectedVehicle").text(sessionStorage.getItem("selectedVehicle") || "Not Selected");
            $("#estimatedFare").text("LKR " + (sessionStorage.getItem("estimatedFare") || "0.00"));
        });

        // 5-second delay before redirecting
        function confirmTrip() {
            document.getElementById("confirm-btn").disabled = true;
            document.getElementById("loading-animation").style.display = "block";
            document.getElementById("loading-text").style.display = "block";

            setTimeout(function () {
                window.location.href = "bookingconfirm.jsp";
            }, 5000);
        }
    </script>
</body>
</html>
