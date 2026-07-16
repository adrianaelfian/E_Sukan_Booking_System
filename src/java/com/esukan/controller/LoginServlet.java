/*
 * MVC: CONTROLLER
 * Receives login requests from login.jsp.
 * Validates user credentials using UserDAO.
 * Redirects users to the appropriate dashboard.
 */
package com.esukan.controller;

import com.esukan.dao.UserDAO;
import com.esukan.model.User;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Retrieve parameter from login.jsp
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role"); 

        //query database layer(to confirm match)
        UserDAO dao = new UserDAO();
        User user = dao.validateUser(email, password, role);

        //authentication success
        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", role);

            // route to correct layout based on role
            if ("MANAGER".equalsIgnoreCase(role)) {
                response.sendRedirect("ManagerDashboardServlet");
            } else {
                response.sendRedirect("StudentDashboardServlet");
            }
        } else {
            //authentication failed
            request.setAttribute("errorMessage", "Invalid Email or Password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
