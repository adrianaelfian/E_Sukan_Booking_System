package com.esukan.controller;

import com.esukan.dao.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role"); 

        UserDAO userDAO = new UserDAO();
        String userFullName = userDAO.validateUser(email, password, role);

        if (userFullName != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", userFullName);
            session.setAttribute("role", role);

            if ("MANAGER".equals(role)) {
                response.sendRedirect("manager-dashboard.html");
            } else {
                response.sendRedirect("student-dashboard.html");
            }
        } else {
            request.setAttribute("errorMessage", "Alamat e-mel, kata laluan atau peranan salah!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
