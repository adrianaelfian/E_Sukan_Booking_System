<%-- 
MVC: VIEW
Display all booking records for managers.
Data is provided by ViewBookingManagerServlet
--%>

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
            table-layout: fixed;
            margin-bottom: 30px;
        }
        
        thead {
            background: #18392b;
            color: #ffffff;
        }
        
        th {
            padding: 12px 15px;
            text-align: left;
            font-weight: 600;
            word-wrap: break-word;
        }
        
        td {
            padding: 10px 15px;
            border-bottom: 1px solid #eee;
            word-wrap: break-word;
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
            padding: 5px 8px;
            border: none;
            border-radius: 4px;
            font-weight: 600;
            cursor: pointer;
            font-size: 12px;
            margin-right: 5px;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: inline-block;
            text-decoration: none;
            text-align: center;
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
      h:nth-child(1) { width: 5%; }  /* ID */
th:nth-child(2) { width: 15%; } /* Name */
th:nth-child(3) { width: 15%; } /* Facility/Equip */
th:nth-child(4) { width: 12%; } /* Date */
th:nth-child(5) { width: 15%; } /* Time/Return */
th:nth-child(6) { width: 10%; } /* Qty/Player */
th:nth-child(7) { width: 10%; } /* Status */
th:nth-child(8) { width: 18%; } /* Action */
    </style>
</head>
<body>
    
    <!-- Main manager booking management container -->
    <div class="container">
        
        <!-- Indicates manager access level -->
        <div class="role-badge">Manager View - All Student Bookings</div>
        
        <h1>Manage Bookings</h1>
        <div class="subtitle">Review and manage all student booking requests</div>

        <%-- Display data from CONTROLLER with approve/reject buttons --%>
        <div class="section-title">Facility Bookings</div>
        
        <!-- Table displaying all facility booking requests -->
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Student Name</th>
                    <th>Facility</th>
                    <th>Date</th>
                    <th>Time</th>
                    <th>Players</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                
                <%-- Display message if no facility bookings are available --%>
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
                                <td>${booking.date}</td>
                                <td>${booking.startTime} - ${booking.endTime}</td>
                                <td>${booking.playerNumber}</td>
                                <td><span class="status-${booking.status.toLowerCase()}">${booking.status}</span></td>
                                <td>
                                    <c:choose>
                                        <%-- Only pending bookings can be approved or rejected --%>
                                        <c:when test="${booking.status == 'Pending'}">
                                            <a href="BookingServlet?action=approve&bookingId=${booking.bookingId}" class="btn-action btn-approve" onclick="return confirm('Approve this booking?')">Approve</a>
                                            <a href="BookingServlet?action=reject&bookingId=${booking.bookingId}" class="btn-action btn-reject" onclick="return confirm('Reject this booking?')">Reject</a>
                                        </c:when>
                                        <c:otherwise>
                                            <%-- Display processed status for approved/rejected bookings --%>
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
        
        <!-- Equipment rental requests submitted by students -->
        <div class="section-title">Equipment Rentals</div>
<table>
    <thead>
        <tr>
            <th>ID</th>
            <th>Student Name</th>
            <th>Equipment Name</th>
            <th>Rental Date</th>
            <th>Return Date</th>
            <th>Quantity</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:choose>
            <%-- Display message if no equipment rentals are available --%>
            <c:when test="${empty equipmentRentals}">
                <tr>
                    <td colspan="7" class="empty-message">No equipment rental requests found.</td>
                </tr>
            </c:when>
            <c:otherwise>
                <c:forEach var="rental" items="${equipmentRentals}">
                    <tr>
                        <td>${rental.rentalId}</td>
                        <td>${rental.studentName}</td>
                        <td>${rental.equipmentName}</td>
                        <td>${rental.rentalDate}</td>
                        <td>${rental.returnDate}</td>
                        <td>${rental.quantity}</td>
                        <td><span class="status-${rental.status.toLowerCase()}">${rental.status}</span></td>
                        <td>
                            <c:choose>
                                <%-- Only pending rentals can be approved or rejected --%>
                                <c:when test="${rental.status == 'Pending'}">
                                    <a href="BookingServlet?action=approve&rentalId=${rental.rentalId}" class="btn-action btn-approve" onclick="return confirm('Approve this rental?')">Approve</a>
                                    <a href="BookingServlet?action=reject&rentalId=${rental.rentalId}" class="btn-action btn-reject" onclick="return confirm('Reject this rental?')">Reject</a>
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
        <!-- Return to Manager Dashboard -->
        <a href="ManagerDashboardServlet" class="btn-back">Back to Dashboard</a>
    </div>
</body>
</html>
