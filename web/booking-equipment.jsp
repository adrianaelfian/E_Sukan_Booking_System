<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Equipment - E-Sukan</title>
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
            max-width: 520px;
            margin: 0 auto;
            background: #ffffff;
            padding: 40px 45px;
            border-radius: 10px;
            box-shadow: 0 0 30px rgba(0, 0, 0, 0.08);
        }
        
        .container h1 {
            color: #1B5E20;
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
            margin-bottom: 20px;
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
            border-color: #2E7D32;
            background: #ffffff;
            box-shadow: 0 0 0 3px rgba(46, 125, 50, 0.08);
        }
        
        .btn-submit {
            width: 100%;
            padding: 13px;
            background: #1B5E20;
            color: #ffffff;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            font-weight: 700;
            cursor: pointer;
            transition: background 0.3s;
            letter-spacing: 1px;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin-top: 5px;
        }
        
        .btn-submit:hover {
            background: #0d3d12;
        }
        
        .btn-back {
            display: inline-block;
            margin-top: 15px;
            color: #888;
            text-decoration: none;
            font-size: 14px;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
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
        <h1>Book Equipment</h1>
        <div class="subtitle">Rent sports equipment for your activities</div>
        
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
            <input type="hidden" name="action" value="bookEquipment">
            
            <div class="form-group">
                <label for="equipmentId">Equipment</label>
                <select name="equipmentId" id="equipmentId" required>
                    <option value="">-- Select Equipment --</option>
                    <option value="1">Badminton Racket - Yonex</option>
                    <option value="2">Badminton Racket - ProTech</option>
                    <option value="3">Shuttlecock (Tube)</option>
                    <option value="4">Futsal Ball - Size 4</option>
                    <option value="5">Futsal Ball - Size 5</option>
                    <option value="6">Basketball</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="quantity">Quantity</label>
                <input type="number" name="quantity" id="quantity" min="1" max="10" value="1" required>
            </div>
            
            <div class="form-group">
                <label for="rentalDate">Rental Date</label>
                <input type="date" name="rentalDate" id="rentalDate" required>
            </div>
            
            <div class="form-group">
                <label for="duration">Duration (hours)</label>
                <select name="duration" id="duration">
                    <option value="1">1 hour</option>
                    <option value="2">2 hours</option>
                    <option value="3">3 hours</option>
                    <option value="4">4 hours</option>
                </select>
            </div>
            
            <button type="submit" class="btn-submit">Submit Rental</button>
        </form>
        
        <a href="student-dashboard.jsp" class="btn-back">Back to Dashboard</a>
    </div>
</body>
</html>
