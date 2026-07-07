<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Bookings - E-Sukan</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; background: #f5f7fb; min-height: 100vh; display: flex; flex-direction: column; }
        
        .header-banner {
            width: 100%;
            background: #1B5E20;
            padding: 40px 20px;
            text-align: center;
            color: white;
        }
        .header-banner h1 { font-size: 2.5rem; letter-spacing: 3px; text-transform: uppercase; }
        .header-banner p { color: #a3b899; font-size: 0.95rem; margin-top: 5px; }

        .main-content {
            flex: 1;
            width: 100%;
            max-width: 1000px;
            margin: 0 auto;
            padding: 40px 20px;
        }

        .section-title {
            color: #1B5E20;
            margin-bottom: 15px;
            font-size: 1.5rem;
            border-left: 5px solid #2E7D32;
            padding-left: 10px;
            margin-top: 20px;
        }

        .table-container {
            background: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 8px 25px rgba(0,0,0,0.05);
            margin-bottom: 40px;
            overflow-x: auto;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            text-align: left;
        }

        th, td {
            padding: 12px 15px;
            border-bottom: 1px solid #ddd;
            font-size: 0.95rem;
        }

        th {
            background-color: #f8f9fa;
            color: #1B5E20;
            font-weight: bold;
        }

        tr:hover { background-color: #f1f3f5; }

        .badge {
            padding: 5px 10px;
            border-radius: 20px;
            font-size: 0.8rem;
            font-weight: bold;
            display: inline-block;
        }

        .badge-approved { background-color: #d4edda; color: #155724; }
        .badge-pending { background-color: #fff3cd; color: #856404; }
        .badge-rejected { background-color: #f8d7da; color: #721c24; }

        .btn-back { 
            display: inline-block;
            margin-top: 10px; 
            color: #666; 
            text-decoration: none; 
            font-size: 0.95rem;
            font-weight: bold;
        }
        
        .btn-back:hover { color: #1B5E20; text-decoration: underline; }
        
        .role-badge {
            display: inline-block;
            padding: 4px 15px;
            border-radius: 4px;
            font-size: 13px;
            font-weight: 600;
            margin-bottom: 15px;
            background: #e8f5e9;
            color: #2e7d32;
        }
    </style>
</head>
<body>

    <div class="header-banner">
        <h1>MY BOOKING HISTORY</h1>
        <p>View and track your facility reservations and equipment rentals.</p>
    </div>

    <div class="main-content">
        
        <div class="role-badge">Student View - Your Bookings Only</div>
        
        <a href="index.html" class="btn-back">← Back to Dashboard</a>

        <h2 class="section-title">Sports Facility Bookings</h2>
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Booking ID</th>
                        <th>Facility Name</th>
                        <th>Date</th>
                        <th>Time Slot</th>
                        <th>Players</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty facilityBookings}">
                            <tr>
                                <td colspan="6" style="text-align:center; color:#999; padding:30px 0;">No facility bookings found.</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="booking" items="${facilityBookings}">
                                <tr>
                                    <td>${booking.bookingId}</td>
                                    <td>${booking.facilityName}</td>
                                    <td>${booking.bookingDate}</td>
                                    <td>${booking.startTime} - ${booking.endTime}</td>
                                    <td>${booking.playerNumber}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${booking.status == 'Approved'}">
                                                <span class="badge badge-approved">Approved</span>
                                            </c:when>
                                            <c:when test="${booking.status == 'Pending'}">
                                                <span class="badge badge-pending">Pending</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge badge-rejected">Rejected</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>

        <h2 class="section-title">Equipment Rentals</h2>
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>Rental ID</th>
                        <th>Equipment Name</th>
                        <th>Rental Date</th>
                        <th>Quantity</th>
                        <th>Duration</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty equipmentRentals}">
                            <tr>
                                <td colspan="6" style="text-align:center; color:#999; padding:30px 0;">No equipment rentals found.</td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="rental" items="${equipmentRentals}">
                                <tr>
                                    <td>${rental.rentalId}</td>
                                    <td>${rental.equipmentName}</td>
                                    <td>${rental.rentalDate}</td>
                                    <td>${rental.quantity}</td>
                                    <td>${rental.duration} hours</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${rental.status == 'Approved'}">
                                                <span class="badge badge-approved">Approved</span>
                                            </c:when>
                                            <c:when test="${rental.status == 'Pending'}">
                                                <span class="badge badge-pending">Pending</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge badge-rejected">Rejected</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </tbody>
            </table>
        </div>

    </div>

</body>
</html>
