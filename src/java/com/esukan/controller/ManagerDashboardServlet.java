/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esukan.controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.esukan.dao.BookingDAO;
import com.esukan.dao.FacilityDAO;
import com.esukan.dao.EquipmentDAO;
import java.util.List;
import com.esukan.model.Booking;

@WebServlet("/ManagerDashboardServlet")
public class ManagerDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        // Temporary dummy data
        BookingDAO bookingDAO = new BookingDAO();
        FacilityDAO facilityDAO = new FacilityDAO();
        EquipmentDAO equipmentDAO = new EquipmentDAO();
        
        request.setAttribute("totalBookings", bookingDAO.getTotalBookings());
        request.setAttribute("totalFacilities", facilityDAO.getTotalFacilities());
        request.setAttribute("totalEquipment", equipmentDAO.getTotalEquipment());
        request.setAttribute("availableEquipment", equipmentDAO.getAvailableEquipment());
        
        List<Booking> bookingList = bookingDAO.getAllBookings();
        request.setAttribute("bookingList",bookingList);
        
        RequestDispatcher dispatcher =
                request.getRequestDispatcher("manager-dashboard.jsp");

        dispatcher.forward(request, response);
    }
}
