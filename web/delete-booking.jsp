<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cancel Booking - E-Sukan</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background: #f0f2f5;
            padding: 20px;
        }
        
        .container {
            max-width: 450px;
            margin: 0 auto;
            background: #ffffff;
            padding: 40px 45px;
            border-radius: 10px;
            box-shadow: 0 0 30px rgba(0, 0, 0, 0.08);
            text-align: center;
        }
        
        .container h1 {
            color: #C62828;
            font-size: 24px;
            font-weight: 700;
            letter-spacing: 1px;
            margin-bottom: 5px;
        }
        
        .container .subtitle {
            color: #888;
            font-size: 14px;
            margin-bottom: 25px;
        }
        
        .warning-box {
            background: #fff3cd;
            color: #856404;
            padding: 12px 15px;
            border-radius: 6px;
            border: 1px solid #ffeeba;
            margin-bottom: 20px;
            font-size: 14px;
        }
        
        .warning-box strong {
            display: block;
            margin-bottom: 3px;
        }
        
        .booking-details {
            background: #f9f9f9;
            border: 1px solid #eee;
            padding: 15px;
            border-radius: 6px;
            margin: 20px 0;
            text-align: left;
            font-size: 14px;
            line-height: 1.8;
        }
        
        .booking-details strong {
            display: block;
            margin-bottom: 5px;
            color: #333;
        }
        
        .btn-group {
            display: flex;
            gap: 12px;
            justify-content: center;
            margin-top: 10px;
        }
        
        .btn-delete {
            padding: 10px 30px;
            background: #C62828;
            color: #ffffff;
            border: none;
            border-radius: 6px;
            font-size: 14px;
            font-weight: 600;
            cursor: pointer;
            transition: background 0.3s;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        
        .btn-delete:hover {
            background: #B71C1C;
        }
        
        .btn-cancel {
            padding: 10px 30px;
            background: #757575;
            color: #ffffff;
            border: none;
            border-radius: 6px;
            font-size: 14px;
            font-weight: 600;
            cursor: pointer;
            text-decoration: none;
            transition: background 0.3s;
            display: inline-block;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        
        .btn-cancel:hover {
            background: #616161;
        }
        
        .btn-back {
            display: inline-block;
            margin-top: 20px;
            color: #888;
            text-decoration: none;
            font-size: 14px;
        }
        
        .btn-back:hover {
            color: #333;
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
        <h1>Cancel Booking</h1>
        
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
        
        <div class="warning-box">
            <strong>Warning:</strong>
            This action cannot be undone. Are you sure you want to cancel this booking?
        </div>
        
        <div class="booking-details">
            <strong>Booking Details:</strong>
            ID: ${booking.bookingId}<br>
            Facility: ${booking.facilityName}<br>
            Date: ${booking.bookingDate}<br>
            Time: ${booking.startTime} - ${booking.endTime}
        </div>
        
        <div class="btn-group">
            <form action="BookingServlet" method="post" style="display: inline;">
                <input type="hidden" name="action" value="cancel">
                <input type="hidden" name="bookingId" value="${booking.bookingId}">
                <button type="submit" class="btn-delete">Yes, Cancel Booking</button>
            </form>
            <a href="view-bookings.jsp" class="btn-cancel">No, Go Back</a>
        </div>
        
        <br>
        <a href="index.jsp" class="btn-back">Back to Dashboard</a>
    </div>
</body>
</html>
