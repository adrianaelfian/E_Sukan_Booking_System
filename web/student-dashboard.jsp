
<%-- 
    Document   : student-dashboard
    Created on : Jul 9, 2026, 8:24:10 AM
    Author     : user
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="com.esukan.model.Booking"%>
<%@page import="com.esukan.model.EquipmentRental"%>


<%
    List<Booking> bookingList =
            (List<Booking>) request.getAttribute("bookingList");
    
    List<EquipmentRental> rentalList = (List<EquipmentRental>) request.getAttribute("rentalList");
    
    if (rentalList == null) {
        rentalList = new java.util.ArrayList<EquipmentRental>();
    }

    Integer totalBookings =
            (Integer) request.getAttribute("totalBookings");

    if (totalBookings == null) {
        totalBookings = 0;
    }

    if (bookingList == null) {
        bookingList = new java.util.ArrayList<Booking>();
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-Sukan Student Dashboard</title>

    <link rel="stylesheet" href="student-dashboard.css">

    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>

<body>

    <!-- Header -->
    <header class="header">

        <h1>E-SUKAN DASHBOARD</h1>

        <nav>
            <a href="logout.jsp" class="logout-btn" style="text-decoration: none; display: inline-flex; align-items: center; justify-content: center;">Logout</a>
        </nav>

    </header>
    
    <h2>Quick Actions</h2>

<div class="quick-actions">

    <a href="booking-facility.jsp" class="action-card">
        <h3>Book a Sport Facility</h3>
        <p>Reserve badminton, futsal or basketball courts.</p>
    </a>

    <a href="booking-equipment.jsp" class="action-card">
        <h3>Rent Sports Equipment</h3>
        <p>Borrow sports equipment for training and games.</p>
    </a>

    <a href="BookingServlet?action=view" class="action-card">
        <h3>View My Bookings</h3>
        <p>Check all current and previous bookings.</p>
    </a>

</div>

    <!-- ========================================== -->
    <!-- JADUAL STATUS RINGKAS DENGAN BUTTON EDIT & CANCEL -->
    <!-- ========================================== -->
    <div class="container" style="margin-bottom: -20px;">
        <div class="table-section" style="margin-top: 10px; margin-bottom: 20px;">
            <h2>My Quick Status Overview</h2>
            <table style="width: 100%; border-collapse: collapse; margin-top: 15px; background: white; border-radius: 8px; overflow: hidden; box-shadow: 0 4px 15px rgba(0,0,0,0.05);">
                <thead>
                    <tr style="background-color: #f8f9fa; text-align: left;">
                        <th style="padding: 12px 15px; color: #18392b; font-weight: bold;">Item / Facility</th>
                        <th style="padding: 12px 15px; color: #18392b; font-weight: bold;">Date</th>
                        <th style="padding: 12px 15px; color: #18392b; font-weight: bold;">Status</th>
                        <th style="padding: 12px 15px; color: #18392b; font-weight: bold;">Actions</th> <!-- Kolum Butang -->
                    </tr>
                </thead>
                
                <p>Total bookings: <%= bookingList.size() %></p>
                
                <tbody>

                <%
                for (Booking booking : bookingList) {
                %>

                <tr>

                <td style="padding:12px 15px;">
                <%= booking.getFacilityName() %>
                </td>

                <td style="padding:12px 15px;">
                <%= booking.getDate() %>
                </td>

                <td style="padding:12px 15px;">

                <%
                if("Pending".equalsIgnoreCase(booking.getStatus())){
                %>

                <span style="background:#fff3cd;color:#856404;padding:5px 10px;border-radius:20px;">
                Pending
                </span>

                <%
                } else {
                %>

               <span style="background:#d4edda;color:#155724;padding:5px 10px;border-radius:20px;">
               <%= booking.getStatus() %>
               </span>

               <%
                }
                %>

            </td>

              <td style="padding:12px 15px;">

              <%
               if("Pending".equalsIgnoreCase(booking.getStatus())){
              %>

        <a href="BookingServlet?action=edit&bookingId=<%= booking.getBookingId() %>"
           style="background:#ffc107;color:black;padding:5px 10px;text-decoration:none;border-radius:4px;margin-left: 10px">
            Edit
        </a>

        <a href="BookingServlet?action=cancel&bookingId=<%= booking.getBookingId() %>"
           style="background:#dc3545;color:white;padding:5px 10px;text-decoration:none;border-radius:4px;margin-left: 10px">
            Cancel
        </a>

        <%
        } else {
        %>

        <button disabled>Edit</button>
        <button disabled>Cancel</button>

        <%
        }
        %>

            </td>

        </tr>

        <%
        }
        %>

</tbody>
               
            </table>
        </div>
    </div>
    <!-- ========================================== -->

    <div class="container">

        <!-- Statistics -->
        <h2>System Overview</h2>

        <div class="summary">

            <div class="card">
                <h3>My Bookings</h3>
                <p><%= totalBookings %></p>
            </div>

            <div class="card">
                <h3>Active Rentals</h3>
                <p>2</p>
            </div>

            <div class="card">
                <h3>Available Facilities</h3>
                <p>8</p>
            </div>

            <div class="card">
                <h3>Available Equipment</h3>
                <p>42</p>
            </div>

        </div>

        <!-- Upcoming Bookings -->
        <div class="table-section">

            <h2>Upcoming Bookings</h2>

            <table>

                <tr>
                    <th>Facility</th>
                    <th>Date</th>
                    <th>Status</th>
                </tr>

                <% 
                    if (bookingList != null && !bookingList.isEmpty()){
                        
                        for (Booking booking : bookingList){
                    %>
                    <tr>
                        <td><%= booking.getFacilityName() %></td>
                        <td><%= booking.getDate() %></td>
                        <td><%= booking.getStatus() %></td> 
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    
                    <tr>
                        <td colspan="3" align="center"> No booking found.</td>
                    </tr>
                    
                    <%
                    }
                    %>

            </table>

        </div>

        <!-- Equipment Rental -->
        <div class="table-section">

            <h2>My Equipment Rentals</h2>

            <table>
                <thead>
                    <tr>
                        <th>Equipment</th>
                        <th>Quantity</th>
                        <th>Return Date</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        if (rentalList != null && !rentalList.isEmpty()){
                            for (EquipmentRental rental : rentalList){
                    %>
                    <tr>
                        <td><%= rental.getEquipmentName() %></td>
                        <td><%= rental.getQuantity() %></td>
                        <td><%= rental.getReturnDate() %></td>
                        <td><%= rental.getStatus() %></td>
                    </tr>
                    <%
                           }
                        } else {
                    %>
                    <tr>
                        <td colspan="4" align="center">No equipment rentals found.</td>
                    </tr>
                    <%
                        }
                     %>
                </tbody>
            </table>

        </div>

        <!-- Charts -->

        <div class="charts">
            
            <div class="chart-item">
                <h2>Peak Usage Hours</h2>
                <canvas id="usageChart"></canvas>
            </div>

            <div class="chart-item">
                <h2>Most Popular Facilities</h2>
                <canvas id="facilityChart"></canvas>
            </div>

        </div>

    </div>

    <script>

        new Chart(document.getElementById("usageChart"), {

            type: "line",

            data: {
                labels: ["8AM","10AM","12PM","2PM","4PM","6PM"],
                datasets: [{
                    label: "Facility Usage",
                    data: [5,12,18,25,20,30],
                    borderColor: "#28a745",
                    backgroundColor: "rgba(40,167,69,0.2)",
                    fill: true,
                    tension: 0.4
                }]
            }

        });

        /* Most Popular Facilities */

        new Chart(document.getElementById("facilityChart"), {

            type: "pie",

            data: {
                labels: [
                    "Badminton Court",
                    "Futsal Court",
                    "Basketball Court",
                    "Football Field"
                ],

                datasets: [{
                    data: [40,30,20,10],

                    backgroundColor: [
                        "#18392b",
                        "#2e8b57",
                        "#f59e0b",
                        "#dc3545"
                    ]
                }]
            }

        });

    </script>

</body>
</html>