<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Welcome to E-Sukan</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
        }

        body { 
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #1B5E20;
            height: 100vh; 
            display: flex;
            justify-content: center;
            align-items: center;
            color: white;
            text-align: center;
            padding: 20px;
        }

        .welcome-container {
            max-width: 600px;
            background: rgba(255, 255, 255, 0.1);
            padding: 50px 40px;
            border-radius: 15px;
            border: 1px solid rgba(255, 255, 255, 0.2);
            box-shadow: 0px 10px 30px rgba(0,0,0,0.3);
        }

        h1 {
            font-size: 3.5rem;
            font-weight: 700;
            letter-spacing: 5px;
            margin-bottom: 15px;
            text-transform: uppercase;
        }

        p {
            font-size: 1.1rem;
            color: #a3b899;
            margin-bottom: 40px;
            line-height: 1.6;
        }

        .btn-group {
            display: flex;
            gap: 20px;
            justify-content: center;
            flex-wrap: wrap;
        }

        .btn {
            padding: 15px 35px;
            font-size: 1rem;
            font-weight: 600;
            text-decoration: none;
            border-radius: 6px;
            transition: all 0.3s ease;
            min-width: 160px;
            display: inline-block;
        }

        .btn-login {
            background-color: #2E7D32;
            color: white;
        }

        .btn-login:hover {
            background-color: #1B5E20;
            transform: translateY(-2px);
        }

        .btn-register {
            background-color: transparent;
            color: white;
            border: 2px solid white;
        }

        .btn-register:hover {
            background-color: white;
            color: #1B5E20;
            transform: translateY(-2px);
        }

        .btn-booking {
            background-color: #FF9800;
            color: white;
        }

        .btn-booking:hover {
            background-color: #F57C00;
            transform: translateY(-2px);
        }

        .role-badge {
            display: inline-block;
            padding: 5px 15px;
            border-radius: 20px;
            font-size: 14px;
            margin-bottom: 20px;
            background: rgba(255,255,255,0.2);
            color: white;
        }

        .user-info {
            margin-bottom: 20px;
            font-size: 14px;
            color: #a3b899;
        }

        .user-info a {
            color: white;
            text-decoration: none;
            background: rgba(255,255,255,0.15);
            padding: 5px 15px;
            border-radius: 4px;
        }

        .user-info a:hover {
            background: rgba(255,255,255,0.25);
        }
    </style>
</head>
<body>

    <div class="welcome-container">
        
        <%
            // Check if user is logged in
            String userName = (String) session.getAttribute("userName");
            String userRole = (String) session.getAttribute("userRole");
            
            if (userName != null && userRole != null) {
                // User is logged in
        %>
                <div class="role-badge">
                    <%= userRole.toUpperCase() %> ACCESS
                </div>
                <h1>E-SUKAN</h1>
                <p>Welcome back, <strong><%= userName %></strong>! Manage your facility and equipment bookings seamlessly.</p>
                
                <div class="user-info">
                    <a href="index.html">Dashboard</a>
                    <a href="logout.jsp">Logout</a>
                </div>
                
                <div class="btn-group">
                    <a href="booking-facility.jsp" class="btn btn-booking">Book Facility</a>
                    <a href="booking-equipment.jsp" class="btn btn-booking">Book Equipment</a>
                </div>
                <br><br>
                <div class="btn-group">
                    <a href="view-bookings-student.jsp" class="btn btn-login">View My Bookings</a>
                </div>
                
                <%
                    // Show manager options if user is manager
                    if ("manager".equals(userRole)) {
                %>
                    <br><br>
                    <div class="btn-group">
                        <a href="view-bookings-manager.jsp" class="btn btn-login">Manage All Bookings</a>
                    </div>
                <%
                    }
                %>
                
        <%
            } else {
                // User is not logged in
        %>
                <h1>E-SUKAN</h1>
                <p>Welcome to the E-Sukan Sports Facility & Equipment Management System. Reserve athletic courts and rent sports equipment seamlessly in one place.</p>
                
                <div class="btn-group">
                    <a href="login.jsp" class="btn btn-login">Sign In</a>
                    <a href="register.jsp" class="btn btn-register">Create Account</a>
                </div>
        <%
            }
        %>
        
    </div>

</body>
</html>
