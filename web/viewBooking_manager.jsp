<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Bookings - E-Sukan</title>
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
            max-width: 1100px;
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
        
        .btn-action {
            padding: 6px 12px;
            border: none;
            border-radius: 4px;
            font-weight: 600;
            cursor: pointer;
            font-size: 0.85rem;
            margin-right: 5px;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        
        .btn-approve {
            background: #2E7D32;
            color: white;
        }
        
        .btn-approve:hover {
            background: #1B5E20;
        }
        
        .btn-reject {
            background: #C62828;
            color: white;
        }
        
        .btn-reject:hover {
            background: #B71C1C;
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
            background: #e3f2fd;
            color: #0d47a1;
        }
        
        .processed-text {
            color: #888;
            font-size: 12px;
            font-style: italic;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="role-badge">Manager View - All Student Bookings</div>
        
        <h1>Manage Bookings</h1>
        <div class="subtitle">Review and manage all student booking requests</div>
        
        <div class="section-title">Facility Bookings</div>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Student</th>
                    <th>Facility</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Players</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty facilityBookings}">
                        <tr>
                            <td colspan="8" class="empty-message">No facility booking requests found.</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="booking" items="${facilityBookings}">
                            <tr>
                                <td>${booking.bookingId}</td>
                                <td>${booking.studentName}</td>
                                <td>${booking.facilityName}</td>
                                <td>${booking.bookingDate}</td>
                                <td>${booking.startTime} - ${booking.endTime}</td>
                                <td>${booking.playerNumber}</td>
                                <td><span class="status-${booking.status.toLowerCase()}">${booking.status}</span></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${booking.status == 'Pending'}">
                                            <a href="BookingServlet?action=approve&bookingId=${booking.bookingId}" class="btn-action btn-approve" onclick="return confirm('Approve this booking?')">Approve</a>
                                            <a href="BookingServlet?action=reject&bookingId=${booking.bookingId}" class="btn-action btn-reject" onclick="return confirm('Reject this booking?')">Reject</a>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="processed-text">Processed</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
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
                    <th>Student</th>
                    <th>Equipment</th>
                    <th>Date</th>
                    <th>Quantity</th>
                    <th>Duration</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:choose>
                    <c:when test="${empty equipmentRentals}">
                        <tr>
                            <td colspan="8" class="empty-message">No equipment rental requests found.</td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach var="rental" items="${equipmentRentals}">
                            <tr>
                                <td>${rental.rentalId}</td>
                                <td>${rental.studentName}</td>
                                <td>${rental.equipmentName}</td>
                                <td>${rental.rentalDate}</td>
                                <td>${rental.quantity}</td>
                                <td>${rental.duration} hours</td>
                                <td><span class="status-${rental.status.toLowerCase()}">${rental.status}</span></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${rental.status == 'Pending'}">
                                            <a href="BookingServlet?action=approveRental&rentalId=${rental.rentalId}" class="btn-action btn-approve" onclick="return confirm('Approve this rental?')">Approve</a>
                                            <a href="BookingServlet?action=rejectRental&rentalId=${rental.rentalId}" class="btn-action btn-reject" onclick="return confirm('Reject this rental?')">Reject</a>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="processed-text">Processed</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </tbody>
        </table>
        
        <a href="dashboard-manager.jsp" class="btn-back">Back to Dashboard</a>
    </div>
</body>
</html>
