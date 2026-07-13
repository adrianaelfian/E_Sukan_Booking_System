     //MVC COMPONENT: VIEW
     //Purpose: Display facility booking form and show success/error message
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Facility - E-Sukan</title>
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
        
        .btn-submit {
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
        
        .btn-submit:hover {
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
        <h1>Book Facility</h1>
        <div class="subtitle">Fill in the details to book a sports facility</div>

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

        <%-- Form sends data to CONTROLLER (BookingServlet) --%>
        <form action="BookingServlet" method="post">
            <input type="hidden" name="action" value="bookFacility">
            
            <div class="form-group">
                <label for="facilityId">Facility</label>
                <select name="facilityId" id="facilityId" required>
                    <option value="">-- Select Facility --</option>
                    <option value="1">Badminton Court A</option>
                    <option value="2">Badminton Court B</option>
                    <option value="3">Futsal Court 1</option>
                    <option value="4">Futsal Court 2</option>
                    <option value="5">Basketball Court</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="bookingDate">Booking Date</label>
                <input type="date" name="bookingDate" id="bookingDate" required>
            </div>
            
            <div class="form-group">
                <label for="startTime">Start Time</label>
                <input type="time" name="startTime" id="startTime" required>
            </div>
            
            <div class="form-group">
                <label for="endTime">End Time</label>
                <input type="time" name="endTime" id="endTime" required>
            </div>
            
            <div class="form-group">
                <label for="numPlayers">Number of Players</label>
                <input type="number" name="numPlayers" id="numPlayers" min="1" max="20" value="2">
            </div>
            
            <button type="submit" class="btn-submit">Submit Booking</button>
        </form>
        
        <a href="index.jsp" class="btn-back">Back to Dashboard</a>
    </div>
</body>
</html>
