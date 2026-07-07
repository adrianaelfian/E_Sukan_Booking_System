package com.esukan.controller;

import com.esukan.dao.BookingDAO;
import com.esukan.model.Booking;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
    
    private BookingDAO bookingDAO;
    
    @Override
    public void init() {
        bookingDAO = new BookingDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("view".equals(action)) {
            viewBookings(request, response);
        } else if ("approve".equals(action)) {
            approveBooking(request, response);
        } else if ("reject".equals(action)) {
            rejectBooking(request, response);
        } else if ("cancel".equals(action)) {
            cancelBooking(request, response);
        } else {
            response.sendRedirect("view-bookings.jsp");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("bookFacility".equals(action)) {
            bookFacility(request, response);
        } else if ("bookEquipment".equals(action)) {
            bookEquipment(request, response);
        } else if ("updateBooking".equals(action)) {
            updateBooking(request, response);
        } else {
            response.sendRedirect("index.html");
        }
    }
    
    private void bookFacility(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        int facilityId = Integer.parseInt(request.getParameter("facilityId"));
        String bookingDate = request.getParameter("bookingDate");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        int playerNumber = Integer.parseInt(request.getParameter("numPlayers"));
        
        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setFacilityId(facilityId);
        booking.setBookingDate(bookingDate);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setPlayerNumber(playerNumber);
        
        boolean success = bookingDAO.addBooking(booking);
        
        if (success) {
            request.setAttribute("message", "Booking submitted successfully. Waiting for approval.");
            request.setAttribute("messageType", "success");
        } else {
            request.setAttribute("message", "Booking failed. Please try again.");
            request.setAttribute("messageType", "error");
        }
        
        request.getRequestDispatcher("booking-facility.jsp").forward(request, response);
    }
    
    private void bookEquipment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("booking-equipment.jsp");
    }
    
    private void viewBookings(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");
        
        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        List<Booking> bookings = bookingDAO.getBookingsByUser(userId);
        request.setAttribute("facilityBookings", bookings);
        request.getRequestDispatcher("view-bookings.jsp").forward(request, response);
    }
    
    private void approveBooking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        bookingDAO.updateBookingStatus(bookingId, "Approved");
        response.sendRedirect("view-bookings.jsp");
    }
    
    private void rejectBooking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        bookingDAO.updateBookingStatus(bookingId, "Rejected");
        response.sendRedirect("view-bookings.jsp");
    }
    
    private void cancelBooking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        bookingDAO.deleteBooking(bookingId);
        response.sendRedirect("view-bookings.jsp");
    }
    
    private void updateBooking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("view-bookings.jsp");
    }
}
