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

        <form action="../confirmTrip" method="post">
            <input type="hidden" name="tripId" id="tripId">
            <input type="hidden" name="passengerName" id="passengerNameInput">
            <input type="hidden" name="pickupLocation" id="pickupLocationInput">
            <input type="hidden" name="dropLocation" id="dropLocationInput">
            <input type="hidden" name="selectedVehicle" id="selectedVehicleInput">
            <input type="hidden" name="totalDistance" id="totalDistanceInput">
            <input type="hidden" name="estimatedFare" id="estimatedFareInput">
            <button type="submit" id="confirm-btn" class="confirm-btn">Confirm & Proceed</button>
        </form>

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
            let tripCode = generateTripCode();
            let passengerName = sessionStorage.getItem("passengerName") || ""; // Keep Passenger Name Blank
            let pickupLocation = sessionStorage.getItem("pickup") || "Not Provided";
            let dropLocation = sessionStorage.getItem("drop") || "Not Provided";
            let totalDistance = sessionStorage.getItem("distance") ? sessionStorage.getItem("distance") + " km" : "Calculating...";
            let selectedVehicle = sessionStorage.getItem("selectedVehicle") || "Not Selected";
            let estimatedFare = "LKR " + (sessionStorage.getItem("estimatedFare") || "0.00");

            // Display in UI
            $("#tripCode").text(tripCode);
            $("#passengerName").text(passengerName);
            $("#pickupLocation").text(pickupLocation);
            $("#dropLocation").text(dropLocation);
            $("#totalDistance").text(totalDistance);
            $("#selectedVehicle").text(selectedVehicle);
            $("#estimatedFare").text(estimatedFare);

            // Assign values to form fields for backend processing
            $("#tripId").val(tripCode);
            $("#passengerNameInput").val(passengerName);
            $("#pickupLocationInput").val(pickupLocation);
            $("#dropLocationInput").val(dropLocation);
            $("#totalDistanceInput").val(sessionStorage.getItem("distance") || "0");
            $("#selectedVehicleInput").val(selectedVehicle);
            $("#estimatedFareInput").val(sessionStorage.getItem("estimatedFare") || "0");
        });
    </script>
</body>
</html>
