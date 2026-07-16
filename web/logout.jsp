<%--
MVC: VIEW
Displays logout confirmation page.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    if (session != null) {
        session.invalidate();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="refresh" content="3;url=login.jsp?status=loggedout">
        <title>Logging Out - E-Sukan</title>
        <style>
            body { 
                font-family: Arial, sans-serif; 
                background-color: #18392b; 
                height: 100vh; 
                display: flex;
                justify-content: center;
                align-items: center;
                color: white;
                margin: 0;
                text-align: center;
            }
            .logout-box {
                background: white;
                padding: 40px;
                border-radius: 12px;
                color: #333;
                box-shadow: 0px 10px 30px rgba(0,0,0,0.3);
                width: 90%;
                max-width: 450px;
            }
            h2 { color: #18392b; margin-bottom: 10px; }
            p { color: #666; margin-bottom: 20px; font-size: 0.95rem; }
            .loader {
                border: 4px solid #f3f3f3;
                border-top: 4px solid #28a745;
                border-radius: 50%;
                width: 40px;
                height: 40px;
                animation: spin 1s linear infinite;
                margin: 0 auto;
            }
            @keyframes spin {
                0% { transform: rotate(0deg); }
                100% { transform: rotate(360deg); }
            }
        </style>
    </head>
    <body>

        <%--View: Shown to user--%>
        <div class="logout-box">
            <h2>Logging out...</h2>
            <p>Your session ended safely. You are now redirected to login page.</p>
            <div class="loader"></div>
        </div>

    </body>
</html>
