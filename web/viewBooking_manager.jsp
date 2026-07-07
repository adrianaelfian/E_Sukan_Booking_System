<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Bookings - E-Sukan</title>
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
            max-width: 1100px;
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

        .btn-action {
            padding: 6px 12px;
            border: none;
            border-radius: 4px;
            font-weight: bold;
            cursor: pointer;
            font-size: 0.85rem;
            margin-right: 5px;
        }
        .btn-approve {
            background-color: #2E7D32;
            color: white;
        }
        .btn-approve:hover {
            background-color: #1B5E20;
        }
        .btn-reject {
            background-color: #C62828;
            color: white;
        }
        .btn-reject:hover {
            background-color: #B71C1C;
        }

        .btn-back {
            display: inline-block;
            margin-top: 10px;
            color: #666;
            text-decoration: none;
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

    <div class="header-banner">
        <h1>INCOMING RESERVATIONS</h1>
        <p>Review, approve, or reject sports facility bookings and equipment rentals.</p>
    </div>

    <div class="main-content">
        
        <div class="role-badge">Manager View - All Student Bookings</div>
        
        <a href="index.html" class="btn-back">← Back to Dashboard</a>

        <h2 class="section-title">Manage Facility Bookings</h2>
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Student</th>
                        <th>Facility</th>
                        <th>Date</th>
                        <th>Time Slot</th>
                        <th>Players</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:choose>
                        <c:when test="${empty facilityBookings}">
                            <tr>
                                <td colspan="8" style="text-align:center; color:#999; padding:30px 0;">No facility booking requests found.</td>
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
        </div>

        <h2 class="section-title">Manage Equipment Rentals</h2>
        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Student</th>
                        <th>Equipment</th>
                        <th>Rental Date</th>
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
                                <td colspan="8" style="text-align:center; color:#999; padding:30px 0;">No equipment rental requests found.</td>
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
        </div>

    </div>

</body>
</html>
