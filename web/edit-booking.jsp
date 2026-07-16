<%--
MVC: VIEW
Allows students to update existing booking information.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Booking - E-Sukan</title>
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
            max-width: 440px;
            width: 100%;
            margin: 0 auto;
            background: #ffffff;
            padding: 40px 45px;
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
            margin-bottom: 28px;
        }
        
        .form-group {
            margin-bottom: 18px;
        }
        
        .form-group label {
            display: block;
            font-weight: 600;
            color: #333;
            font-size: 14px;
            margin-bottom: 5px;
        }
        
        .form-group input,
        .form-group select {
            width: 100%;
            padding: 11px 16px;
            border: 1px solid #ddd;
            border-radius: 6px;
            font-size: 14px;
            box-sizing: border-box;
            background: #fafafa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            transition: border-color 0.3s;
        }
        
        .form-group input:focus,
        .form-group select:focus {
            outline: none;
            border-color: #18392b;
            background: #ffffff;
            box-shadow: 0 0 0 3px rgba(24, 57, 43, 0.08);
        }
        
        .form-group input[readonly] {
            background: #f0f0f0;
            color: #666;
        }
        
        .btn-update {
            width: 100%;
            padding: 12px;
            background: #18392b;
            color: #ffffff;
            border: none;
            border-radius: 6px;
            font-size: 15px;
            font-weight: 700;
            cursor: pointer;
            transition: background 0.3s;
            letter-spacing: 1px;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin-top: 5px;
        }
        
        .btn-update:hover {
            background: #0d2a1f;
        }
        
        .btn-back {
            display: inline-block;
            margin-top: 15px;
            color: #666;
            text-decoration: none;
            font-size: 14px;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        
        .btn-back:hover {
            color: #18392b;
            text-decoration: underline;
        }
        
        .message {
            padding: 10px;
            border-radius: 6px;
            margin-bottom: 20px;
            text-align: center;
            font-size: 14px;
        }
        
        .success {
            background: #e8f5e9;
            color: #2e7d32;
            border: 1px solid #c8e6c9;
        }
        
        .error {
            background: #fff3f3;
            color: #d32f2f;
            border: 1px solid #ffcdd2;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Edit Booking</h1>
        <div class="subtitle">Modify your existing booking details</div>

        <%-- Display message from CONTROLLER --%>
        <%
            String message = (String) request.getAttribute("message");
            if (message != null) {
                String msgClass = "success";
                if (request.getAttribute("messageType") != null && 
                    request.getAttribute("messageType").equals("error")) {
                    msgClass = "error";
                }
        %>
            <div class="message <%= msgClass %>"><%= message %></div>
        <% } %>
        
        <form action="BookingServlet" method="post">
            <%-- Gunakan c:choose untuk tentukan action --%>
            <c:choose>
                <c:when test="${not empty booking}">
                    <input type="hidden" name="action" value="updateBooking">
                    <input type="hidden" name="bookingId" value="${booking.bookingId}">
            
                    <div class="form-group">
                        <label>Facility Name</label>
                        <input type="text" value="${booking.facilityName}" readonly>
                    </div>
                    <div class="form-group">
                        <label>Date</label>
                        <input type="date" name="date" value="${booking.date}">
                    </div>
                    <div class="form-group">
                        <label>Start Time</label>
                        <input type="time" name="startTime" value="${booking.startTime}">
                    </div>
                    <div class="form-group">
                        <label>End Time</label>
                        <input type="time" name="endTime" value="${booking.endTime}">
                    </div>
                    <div class="form-group">
                        <label>Player Number</label>
                        <input type="number" name="playerNumber" value="${booking.playerNumber}">
                    </div>
                </c:when>
        
                <c:when test="${not empty rental}">
                    <input type="hidden" name="action" value="updateRental">
                    <input type="hidden" name="rentalId" value="${rental.rentalId}">
            
                    <div class="form-group">
                        <label>Equipment Name</label>
                        <input type="text" value="${rental.equipmentName}" readonly>
                    </div>
                    <div class="form-group">
                        <label>Rental Date</label>
                        <input type="date" name="date" value="${rental.rentalDate}">
                    </div>
                    <div class="form-group">
                        <label>Return Date</label>
                        <input type="date" name="returnDate" value="${rental.returnDate}">
                    </div>
                </c:when>
            </c:choose>
    
            <button type="submit" class="btn-update">Update Record</button>
        </form>
        
        <a href="BookingServlet?action=view" class="btn-back">Back to Bookings</a>
    </div>
</body>
</html>