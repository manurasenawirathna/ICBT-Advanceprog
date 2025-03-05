<%-- 
    Document   : vehicle-selection.jsp
    Created on : Feb 20, 2025, 6:08:39 PM
    Author     : manur
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Select Vehicle</title>
    
    <!-- Google Fonts (Poppins) -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
    
    <link rel="stylesheet" type="text/css" href="../css/vehiclestyle.css">
    <script src="https://cdn.lordicon.com/lordicon.js"></script>
</head>
<body>

    <!-- Fullscreen Video Background -->
    <video autoplay loop muted playsinline id="bg-video">
        <source src="../videos/test889.mp4" type="video/mp4">
        Your browser does not support the video tag.
    </video>

    <!-- Vehicle Selection Container -->
    <div class="vehicle-container">
        <h2>Select Your Vehicle</h2>

        <!-- Display Pickup, Drop, and Distance -->
        <p><strong>Pickup Address:</strong> <span id="pickupDisplay">Loading...</span></p>
        <p><strong>Drop Location:</strong> <span id="dropDisplay">Loading...</span></p>
        <p><strong>Total Distance:</strong> <span id="distanceDisplay">0 km</span></p>

        <form onsubmit="saveTripDetails(event)">
            <div class="vehicle-options">
                <label class="vehicle-card">
                    <input type="radio" name="vehicle" value="tuk" required>
                    <lord-icon src="https://cdn.lordicon.com/lwzsvzkp.json" trigger="hover"></lord-icon>
                    <div class="vehicle-info">
                        <span class="vehicle-name">Tuk (LKR 30/km)</span>
                        <span class="vehicle-capacity">🚶‍♂️ 2</span>
                    </div>
                </label>

                <label class="vehicle-card">
                    <input type="radio" name="vehicle" value="bike">
                    <lord-icon src="https://cdn.lordicon.com/nyzgaoal.json" trigger="hover"></lord-icon>
                    <div class="vehicle-info">
                        <span class="vehicle-name">Bike (LKR 25/km)</span>
                        <span class="vehicle-capacity">🚶‍♂️ 1</span>
                    </div>
                </label>

                <label class="vehicle-card">
                    <input type="radio" name="vehicle" value="car">
                    <lord-icon src="https://cdn.lordicon.com/grcrdebf.json" trigger="hover"></lord-icon>
                    <div class="vehicle-info">
                        <span class="vehicle-name">Car (LKR 50/km)</span>
                        <span class="vehicle-capacity">🚶‍♂️ 4</span>
                    </div>
                </label>

                <label class="vehicle-card">
                    <input type="radio" name="vehicle" value="van">
                    <lord-icon src="https://cdn.lordicon.com/zzjjvkam.json" trigger="hover"></lord-icon>
                    <div class="vehicle-info">
                        <span class="vehicle-name">Mini Van (LKR 75/km)</span>
                        <span class="vehicle-capacity">🚶‍♂️ 5</span>
                    </div>
                </label>
            </div>
            <button type="submit" class="confirm-btn">Confirm Booking</button>
        </form>
    </div>

    <script>
        // Retrieve and Display Session Data
        document.getElementById("pickupDisplay").innerText = sessionStorage.getItem("pickup") || "Not Provided";
        document.getElementById("dropDisplay").innerText = sessionStorage.getItem("drop") || "Not Provided";
        document.getElementById("distanceDisplay").innerText = sessionStorage.getItem("distance") ? sessionStorage.getItem("distance") + " km" : "0 km";

        function saveTripDetails(event) {
            event.preventDefault(); 

            let passengerName = prompt("Enter Passenger Name:", "John Doe"); 
            let selectedVehicle = document.querySelector('input[name="vehicle"]:checked');
            
            if (!selectedVehicle) {
                alert("Please select a vehicle!");
                return;
            }

            let vehicleType = selectedVehicle.value;
            let distance = sessionStorage.getItem("distance") || "0"; 
            let estimatedFare = calculateFare(vehicleType, distance);

            sessionStorage.setItem("passengerName", passengerName);
            sessionStorage.setItem("selectedVehicle", vehicleType);
            sessionStorage.setItem("estimatedFare", estimatedFare);

            window.location.href = "tripdetailreview.jsp";
        }

        function calculateFare(vehicle, distance) {
            let rates = { "tuk": 30, "bike": 25, "car": 50, "van": 75 };
            return (rates[vehicle] * parseFloat(distance)).toFixed(2);
        }

        // Add black border to selected item
        document.querySelectorAll('.vehicle-card').forEach(card => {
            card.addEventListener('click', function() {
                document.querySelectorAll('.vehicle-card').forEach(c => c.classList.remove('selected'));
                this.classList.add('selected');
            });
        });
    </script>
</body>
</html>
