<%-- 
    Document   : bookingconfirm.jsp
    Created on : Feb 20, 2025, 9:17:29â¯PM
    Author     : manur
--%>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page import="com.mycompany.projectx1.model.Driver" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Confirmed</title>
    <link rel="stylesheet" type="text/css" href="../css/bookingconfirmstyle.css">
</head>
<body>

    <video autoplay loop muted playsinline id="bg-video">
        <source src="../videos/test889.mp4" type="video/mp4">
    </video>

    <div class="confirm-container">
        <h2>Your Driver is on the Way 🚖</h2>

        <%
            Driver driver = (Driver) session.getAttribute("driver");
            String tripId = request.getParameter("tripId");
        %>

        <div id="driver-info">
            <p><b>Driver Name:</b> <%= driver != null ? driver.getDriverName() : "Not Available" %></p>
            <p><b>Contact Number:</b> <%= driver != null ? driver.getContactNumber() : "Not Available" %></p>
            <p><b>Vehicle Model:</b> <%= driver != null ? driver.getVehicleModel() : "Not Available" %></p>
            <p><b>Vehicle Color:</b> <%= driver != null ? driver.getVehicleColor() : "Not Available" %></p>
            <p><b>Vehicle Number:</b> <%= driver != null ? driver.getVehicleNumber() : "Not Available" %></p>
        </div>

        <form action="../bookingConfirm" method="post">
            <input type="hidden" name="tripId" value="<%= tripId %>">
            <button type="submit" name="action" value="done" class="confirm-btn">Done</button>
            <button type="submit" name="action" value="cancel" class="cancel-btn">Cancel</button>
        </form>
    </div>

</body>
</html>
