<%-- 
    Document   : pendingtrips.jsp
    Created on : Mar 11, 2025, 3:39:35 PM
    Author     : manur
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.mycompany.projectx1.service.UserSentricfetchtipService" %>
<%@ page import="com.mycompany.projectx1.model.ConfirmedTrip" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pending Trips</title>
    <link rel="stylesheet" type="text/css" href="../css/pendingtripsstyle.css">
</head>
<body>

    <h2>Pending Trips</h2>

    <table border="1">
        <thead>
            <tr>
                <th>Trip ID</th>
                <th>Passenger Name</th>
                <th>Pickup Location</th>
                <th>Drop Location</th>
                <th>Selected Vehicle</th>
                <th>Total Distance</th>
                <th>Estimated Fare</th>
                <th>Driver Name</th>
                <th>Contact Number</th>
                <th>Vehicle Model</th>
                <th>Vehicle Color</th>
                <th>Vehicle Number</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <%
                UserSentricfetchtipService tripService = new UserSentricfetchtipService();
                List<ConfirmedTrip> pendingTrips = tripService.getConfirmedTrips(); // ✅ Fetch only "Confirmed" trips
                
                if (pendingTrips.isEmpty()) {
            %>
                    <tr><td colspan="13">No pending trips found.</td></tr>
            <%
                } else {
                    for (ConfirmedTrip trip : pendingTrips) {
            %>
                        <tr>
                            <td><%= trip.getTripId() %></td>
                            <td><%= trip.getPassengerName() %></td>
                            <td><%= trip.getPickupLocation() %></td>
                            <td><%= trip.getDropLocation() %></td>
                            <td><%= trip.getSelectedVehicle() %></td>
                            <td><%= trip.getTotalDistance() %> km</td>
                            <td>LKR <%= trip.getEstimatedFare() %></td>
                            <td><%= trip.getDriverName() %></td>
                            <td><%= trip.getContactNumber() %></td>
                            <td><%= trip.getVehicleModel() %></td>
                            <td><%= trip.getVehicleColor() %></td>
                            <td><%= trip.getVehicleNumber() %></td>
                            <td>
                                <form action="../completeTrip" method="post">
                                    <input type="hidden" name="tripId" value="<%= trip.getTripId() %>">
                                    <button type="submit" class="complete-btn">Complete</button>
                                </form>
                            </td>
                        </tr>
            <%
                    }
                }
            %>
        </tbody>
    </table>

</body>
</html>

