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

        //Controller: extract input form parameters passed from login.jsp
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role"); 

        UserDAO dao = new UserDAO();
        User user = dao.validateUser(email, password, role);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", role);

            if ("MANAGER".equalsIgnoreCase(role)) {
                response.sendRedirect("ManagerDashboardServlet");
            } else {
                response.sendRedirect("StudentDashboardServlet");
            }
        } else {
            request.setAttribute("errorMessage", "Invalid Email or Password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
