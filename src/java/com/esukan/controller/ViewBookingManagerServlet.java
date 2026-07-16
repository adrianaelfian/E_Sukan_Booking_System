/*
 * MVC: CONTROLLER
 * Retrieves all booking records for managers.
 * - Call BookingDAO
 * - Send booking list to viewBooking_manager.jsp
 */

package com.esukan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import com.esukan.dao.BookingDAO;
import com.esukan.model.Booking;
import com.esukan.model.EquipmentRental;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adriana
 */
@WebServlet(name = "ViewBookingManagerServlet", urlPatterns = {"/ViewBookingManagerServlet"})
public class ViewBookingManagerServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BookingDAO bookingDAO = new BookingDAO();

        List<Booking> facilityList = bookingDAO.getAllBookings();
        List<EquipmentRental> rentalList = bookingDAO.getAllEquipmentRentals();

        request.setAttribute("facilityBookings", facilityList);
        request.setAttribute("equipmentRentals", rentalList);
        
        request.setAttribute("totalBookings", bookingDAO.getTotalBookings()); 
        request.setAttribute("totalFacilities", bookingDAO.getTotalFacilities());
        request.setAttribute("totalEquipment", bookingDAO.getTotalEquipment());
        request.setAttribute("availableEquipment", bookingDAO.getAvailableEquipment());

        request.getRequestDispatcher("viewBooking_manager.jsp").forward(request, response);}
        // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
        /**
         * Handles the HTTP <code>GET</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Handles the HTTP <code>POST</code> method.
         *
         * @param request servlet request
         * @param response servlet response
         * @throws ServletException if a servlet-specific error occurs
         * @throws IOException if an I/O error occurs
         */
        @Override
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }
}

