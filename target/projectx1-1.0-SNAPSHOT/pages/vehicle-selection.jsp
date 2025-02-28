<%-- 
    Document   : vehicle-selection.jsp
    Created on : Feb 20, 2025, 6:08:39 PM
    Author     : manur
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Select Vehicle</title>
    <link rel="stylesheet" type="text/css" href="../css/vehiclestyle.css">
    <script src="https://kit.fontawesome.com/a076d05399.js" crossorigin="anonymous"></script> <!-- For Icons -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> <!-- jQuery for session storage -->
</head>
<body>
    <div class="vehicle-container">
        <h2>Select Your Vehicle</h2>
        <form onsubmit="saveTripDetails(event)">
            <div class="vehicle-options">
                <label>
                    <input type="radio" name="vehicle" value="tuk" required>
                    <i class="fas fa-motorcycle"></i> Tuk (LKR 30/km)
                </label>
                <label>
                    <input type="radio" name="vehicle" value="bike">
                    <i class="fas fa-bicycle"></i> Bike (LKR 25/km)
                </label>
                <label>
                    <input type="radio" name="vehicle" value="car">
                    <i class="fas fa-car"></i> Car (LKR 50/km)
                </label>
                <label>
                    <input type="radio" name="vehicle" value="van">
                    <i class="fas fa-shuttle-van"></i> Mini Van (LKR 75/km)
                </label>
            </div>
            <button type="submit" class="confirm-btn">Confirm Booking</button>
        </form>
    </div>

    <script>
        function saveTripDetails(event) {
            event.preventDefault(); // Prevent form submission

            let passengerName = prompt("Enter Passenger Name:", "John Doe"); // Ask for name
            let selectedVehicle = document.querySelector('input[name="vehicle"]:checked');
            
            if (!selectedVehicle) {
                alert("Please select a vehicle!");
                return;
            }

            let vehicleType = selectedVehicle.value;
            let distance = sessionStorage.getItem("totalDistance") || "0"; // Retrieved from DistanceServlet
            let estimatedFare = calculateFare(vehicleType, distance);

            // Store details in sessionStorage
            sessionStorage.setItem("passengerName", passengerName);
            sessionStorage.setItem("selectedVehicle", vehicleType);
            sessionStorage.setItem("estimatedFare", estimatedFare);

            // Redirect to trip summary page
            window.location.href = "tripdetailreview.jsp";
        }

        function calculateFare(vehicle, distance) {
            let rates = { "tuk": 30, "bike": 25, "car": 50, "van": 75 }; // Per km fare
            return (rates[vehicle] * parseFloat(distance)).toFixed(2);
        }
    </script>
</body>
</html>
