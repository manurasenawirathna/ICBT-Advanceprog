<%-- 
    Document   : bookingconfirm.jsp
    Created on : Feb 20, 2025, 9:17:29 PM
    Author     : manur
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Confirmed</title>

    <!-- Google Fonts (Poppins) -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="../css/bookingconfirmstyle.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body onload="generateDriverDetails()">

    <!-- Fullscreen Video Background -->
    <video autoplay loop muted playsinline id="bg-video">
        <source src="../videos/test889.mp4" type="video/mp4">
        Your browser does not support the video tag.
    </video>

    <!-- Confirmation Box -->
    <div class="confirm-container">
        <h2>Your Driver is on the Way 🚖</h2>
        <p id="loading-text">Fetching Driver Details...</p>

        <div id="driver-info" style="display: none;">
            <p><b>Driver Name:</b> <span id="driverName"></span></p>
            <p><b>Contact Number:</b> <span id="driverContact"></span></p>
            <p><b>Vehicle Model:</b> <span id="vehicleModel"></span></p>
            <p><b>Vehicle Color:</b> <span id="vehicleColor"></span></p>
            <p><b>Vehicle Number:</b> <span id="vehicleNumber"></span></p>
            <p class="message">Your driver is on the way! Hang tight. 🚗💨</p>
            <button class="cancel-btn" onclick="cancelBooking()">Cancel Booking</button>
        </div>
    </div>

    <script>
        function generateDriverDetails() {
            setTimeout(function () {
                $("#loading-text").hide();
                $("#driver-info").fadeIn();

                // Static driver details (can be dynamic later)
                document.getElementById("driverName").innerText = "John Wick";
                document.getElementById("driverContact").innerText = "+94 76 123 4567";
                document.getElementById("vehicleModel").innerText = "Toyota Prius";

                // Randomly generate vehicle number & color
                let colors = ["Red", "Blue", "Black", "White", "Silver", "Green"];
                let plate = "ABC-" + Math.floor(1000 + Math.random() * 9000); // Example: ABC-5678

                document.getElementById("vehicleColor").innerText = colors[Math.floor(Math.random() * colors.length)];
                document.getElementById("vehicleNumber").innerText = plate;
            }, 3000); // Delay before showing driver details
        }

        function cancelBooking() {
            alert("Your booking has been canceled.");
            window.location.href = "booking.jsp"; // Redirect to booking page
        }
    </script>
</body>
</html>
