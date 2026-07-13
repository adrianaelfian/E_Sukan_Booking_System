// MVC COMPONENT: CONTROLLER
// Purpose: Handles all booking requests, coordinates Model and View
package com.esukan.controller;

import com.esukan.dao.BookingDAO;
import com.esukan.model.Booking;
import com.esukan.model.User;
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

//  MODEL REFERENCES 
    private BookingDAO bookingDAO;
    
    @Override
    public void init() {
        bookingDAO = new BookingDAO();
    }
    
    //  HANDLE GET REQUESTS 
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
 //  HANDLE POST REQUESTS 
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
            response.sendRedirect("StudentDashboardServlet");
        }
    }

    //  CONTROLLER METHOD: Book Facility 
    private void bookFacility(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                
        // STEP 1: Get data from VIEW (form)
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("user")==null){
            response.sendRedirect("login.jsp");
            return;
        }
        
        User user = (User) session.getAttribute("user");
        
        int facilityId = Integer.parseInt(request.getParameter("facilityId"));
        String bookingDate = request.getParameter("bookingDate");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        int playerNumber = Integer.parseInt(request.getParameter("numPlayers"));

        // STEP 2: Create MODEL object
        Booking booking = new Booking();
        booking.setUserId(user.getId());
        booking.setFacilityId(facilityId);
        booking.setBookingDate(bookingDate);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setPlayerNumber(playerNumber);

        // STEP 3: Save to database via MODEL (DAO)
        boolean success = bookingDAO.addBooking(booking);

        // STEP 4: Send response back to VIEW
        if (success) {
             response.sendRedirect("BookingServlet?action=view");
        } else {
            request.setAttribute("message", "Booking failed. Please try again.");
            request.setAttribute("messageType", "error");

      // Forward back to VIEW
            request.getRequestDispatcher("booking-facility.jsp").forward(request, response);
        }
    }
    
    //  CONTROLLER METHOD: Book Equipment 
    private void bookEquipment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("StudentDashboardServlet");
    }
    //  CONTROLLER METHOD: View Bookings 
    private void viewBookings(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    // STEP 1: Get data from VIEW (form)
        HttpSession session = request.getSession(false);
        
        if (session == null || session.getAttribute("user")==null){
            response.sendRedirect("login.jsp");
            return;
        }
        
        User user = (User) session.getAttribute("user");
                
        // STEP 2: Get data from MODEL (DAO)
        List<Booking> bookings = bookingDAO.getBookingsByUser(user.getId());
        // STEP 3: Send data to VIEW
        request.setAttribute("facilityBookings", bookings);
        // STEP 4: Forward to VIEW
        request.getRequestDispatcher("viewBookings_student.jsp")
               .forward(request,response);    }
        //  CONTROLLER METHOD: Approve Booking 
    private void approveBooking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        bookingDAO.updateBookingStatus(bookingId, "Approved");
        response.sendRedirect("ManagerDashboardServlet");
    }
     //  CONTROLLER METHOD: Reject Booking 
    private void rejectBooking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
                
        // Update status via MODEL (DAO)
        bookingDAO.updateBookingStatus(bookingId, "Rejected");
        response.sendRedirect("ManagerDashboardServlet");
    }
    // ========== CONTROLLER METHOD: Cancel Booking ==========
    private void cancelBooking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        // Delete via MODEL (DAO)
        bookingDAO.deleteBooking(bookingId);
        response.sendRedirect("StudentDashboardServlet");
    }
    //  CONTROLLER METHOD: Update Booking 
    private void updateBooking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("StudentDashboardServlet");
    }
}
