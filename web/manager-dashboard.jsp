<%-- 
    Document   : manager-dashboard
    Created on : Jul 9, 2026, 8:24:32 AM
    Author     : user
--%>

<%@page import="java.util.List"%>
<%@page import="com.esukan.model.Booking"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>E-Sukan Manager Dashboard</title>

    <link rel="stylesheet" href="manager-dashboard.css">

    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>

<body>

    <header class="header">

        <h1>E-SUKAN MANAGER DASHBOARD</h1>

        <nav>
            <a href="viewBooking_manager.jsp">Booking Management</a>            
            <button class="logout-btn">Logout</button>
        </nav>

    </header>

    <div class="container">

        <h2>System Overview</h2>

        <div class="summary">

            <div class="card">
                <h3>Total Bookings</h3>
                <p><%= request.getAttribute("totalBookings") %></p>
            </div>

            <div class="card">
                <h3>Total Facilities</h3>
                <p><%= request.getAttribute("totalFacilities") %></p>
            </div>

            <div class="card">
                <h3>Total Equipment</h3>
                <p><%= request.getAttribute("totalEquipment") %></p>
            </div>

            <div class="card">
                <h3>Available Equipment</h3>
                <p><%= request.getAttribute("availableEquipment") %></p>
            </div>

        </div>

        <!-- Recent Booking Requests -->

        <div class="table-section">

            <h2>Recent Booking Requests</h2>

            <table>

                <tr>
                    <th>Student</th>
                    <th>Facility</th>
                    <th>Date</th>
                    <th>Status</th>
                </tr>

                <% 
                    List<Booking> bookingList = (List<Booking>) request.getAttribute("bookingList");
                    
                    if (bookingList !=null && !bookingList.isEmpty()){
                        for (Booking booking : bookingList){
                %>
                
                <tr>
                    <td><%= booking.getStudentName()%></td>
                    <td><%= booking.getFacilityName()%></td>
                    <td><%= booking.getBookingDate()%></td>
                    <td><%= booking.getStatus()%></td>
                </tr>
                
                <%
                        }
                    } else {
                %>
                
                <tr>
                    <td colspan="4" style="text-align: center;">
                        No booking records found.
                    </td>
                </tr>
                <%
                }
                %>

            </table>

        </div>

        <!-- Equipment Alerts -->

        <div class="table-section">

            <h2>Equipment Alerts</h2>

            <table>

                <tr>
                    <th>Equipment</th>
                    <th>Status</th>
                </tr>

                <tr>
                    <td>Football #3</td>
                    <td>Damaged</td>
                </tr>

                <tr>
                    <td>Basketball #5</td>
                    <td>Maintenance</td>
                </tr>

                <tr>
                    <td>Volleyball Net A</td>
                    <td>Damaged</td>
                </tr>

            </table>

        </div>

        <!-- Charts -->
        <div class="charts">

            <div class="chart-item">
                <h2>Monthly Bookings</h2>
                <canvas id="bookingChart"></canvas>
            </div>

            <div class="chart-item">
                <h2>Equipment Availability</h2>
                <canvas id="equipmentChart"></canvas>
            </div>

        </div>

    </div>

    <script>
        
        /* Monthly Bookings */

        new Chart(document.getElementById("bookingChart"), {

            type: "bar",

            data: {
                labels: ["Jan","Feb","Mar","Apr","May","Jun"],
                datasets: [{
                    label: "Total Bookings",
                    data: [45,60,55,80,90,75],
                    backgroundColor: "#18392b"
                }]
            }

        });


        new Chart(document.getElementById("equipmentChart"), {

            type: "doughnut",

            data: {
                labels: ["Available","Borrowed","Maintenance"],
                datasets: [{
                    data: [42,10,4],
                    backgroundColor: [
                        "#28a745",
                        "#dc3545",
                        "#ffc107"
                    ]
                }]
            }

        });

    </script>

</body>

</html>

