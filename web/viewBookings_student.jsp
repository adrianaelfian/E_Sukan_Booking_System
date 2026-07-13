//MVC COMPONENT: VIEW
//Purpose: Display student's own bookings retrieved from CONTROLLER

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>My Bookings - E-Sukan</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #18392b;
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        
        .container {
            max-width: 1000px;
            width: 100%;
            margin: 0 auto;
            background: #ffffff;
            padding: 35px 40px;
            border-radius: 10px;
            box-shadow: 0 0 30px rgba(0, 0, 0, 0.3);
        }
        
        .container h1 {
            color: #18392b;
            font-size: 26px;
            font-weight: 700;
            text-align: center;
            letter-spacing: 1px;
            margin-bottom: 5px;
        }
        
        .container .subtitle {
            color: #888;
            text-align: center;
            font-size: 14px;
            margin-bottom: 25px;
        }
        
        .section-title {
            background: #e8f5e9;
            color: #18392b;
            padding: 8px 15px;
            border-radius: 4px;
            font-weight: 600;
            font-size: 14px;
            margin: 25px 0 15px 0;
        }
        
        table {
            width: 100%;
            border-collapse: collapse;
            font-size: 14px;
        }
        
        thead {
            background: #18392b;
            color: #ffffff;
        }
        
        th {
            padding: 12px 15px;
            text-align: left;
            font-weight: 600;
        }
        
        td {
            padding: 10px 15px;
            border-bottom: 1px solid #eee;
        }
        
        tbody tr:hover {
            background: #f5f7fa;
        }
        
        .status-pending {
            background: #fff3cd;
            color: #856404;
            padding: 3px 10px;
            border-radius: 4px;
            font-size: 12px;
            font-weight: 600;
            display: inline-block;
        }
        
        .status-approved {
            background: #d4edda;
            color: #155724;
            padding: 3px 10px;
            border-radius: 4px;
            font-size: 12px;
            font-weight: 600;
            display: inline-block;
        }
        
        .status-rejected {
            background: #f8d7da;
            color: #721c24;
            padding: 3px 10px;
            border-radius: 4px;
            font-size: 12px;
            font-weight: 600;
            display: inline-block;
        }
        
        .btn-back {
            display: inline-block;
            margin-top: 20px;
            color: #666;
            text-decoration: none;
            font-size: 14px;
        }
        
        .btn-back:hover {
            color: #18392b;
            text-decoration: underline;
        }
        
        .empty-message {
            text-align: center;
            color: #999;
            padding: 30px 0;
            font-size: 14px;
        }
        
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
    <div class="container">
        <div class="role-badge">Student View - Your Bookings Only</div>
        
        <h1>My Bookings</h1>
        <div class="subtitle">View your facility and equipment bookings</div>

        <%-- Display data from CONTROLLER using JSTL --%>
        <div class="section-title">Facility Bookings</div>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Facility</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Players</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty facilityBookings}">
                        <tr>
                            <td colspan="6" class="empty-message">No facility bookings found.</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="booking" items="${facilityBookings}">
                            <tr>
                                <td>${booking.bookingId}</td>
                                <td>${booking.facilityName}</td>
                                <td>${booking.date}</td>
                                <td>${booking.startTime} - ${booking.endTime}</td>
                                <td>${booking.playerNumber}</td>
                                <td><span class="status-${booking.status.toLowerCase()}">${booking.status}</span></td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
        
        <div class="section-title">Equipment Rentals</div>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Equipment</th>
                    <th>Date</th>
                    <th>Quantity</th>
                    <th>Duration</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty equipmentRentals}">
                        <tr>
                            <td colspan="6" class="empty-message">No equipment rentals found.</td>
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
                                <td><span class="status-${rental.status.toLowerCase()}">${rental.status}</span></td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
        
        <a href="student-dashboard.jsp" class="btn-back">Back to Dashboard</a>
    </div>
</body>
</html>

