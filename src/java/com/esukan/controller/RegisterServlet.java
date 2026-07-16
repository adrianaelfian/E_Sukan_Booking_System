/*
 * MVC: CONTROLLER
 * Handles new user registration.
 * - Receive registration form data
 * - Validate user input
 * - Call UserDAO to insert new user
 * - Redirect user after registration
 */

package com.esukan.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.esukan.model.User;
import com.esukan.dao.UserDAO; 

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO = new UserDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Take data from register.jsp from
        String idStr = request.getParameter("id");
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String phoneNumber = request.getParameter("phoneNumber");
        String role = request.getParameter("role");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        int id = 0;
        if (idStr != null && !idStr.trim().isEmpty()) {
            try {
                id = Integer.parseInt(idStr);
            } catch (NumberFormatException e) {
                id = 0; // Fallback to default
            }
        }

        // 2. Validation Process
        String errorMsg = null;

        // Check A: Make sure the password length is not short than 6
        if (password.length() < 6) {
            errorMsg = "The length of the password atleast 6 characters!";
        } 
        // Check B: Make sure Password and Confirm Password is same
        else if (!password.equals(confirmPassword)) {
            errorMsg = "Password confirmation does not match!";
        }

        // 3. IF VALIDATION FAILED
        if (errorMsg != null) {
            // Error message to register.jsp page
            request.setAttribute("errorMessage", errorMsg);
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return; // Stop the registration process
        }

        // 4. IF VALIDATION SUCCESS  (KEEP DATA)
        User newUser = new User(fullName, email, phoneNumber, role, password);
        boolean isSuccess = userDAO.registerUser(newUser);

        if (isSuccess) {
            // IF SUCCESS, SEND USER TO LOGIN PAGE
            response.sendRedirect("login.jsp?status=success");
        } else {
            // IF FAILED (EXAMPLE: Database error OR Same email as others)
            request.setAttribute("errorMessage", "Registration Failed. Please Try Again!");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}
