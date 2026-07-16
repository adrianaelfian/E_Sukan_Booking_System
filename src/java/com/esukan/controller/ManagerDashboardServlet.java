/*
 * MVC: CONTROLLER
 * Retrieves dashboard information for managers.
 * - Call DashboardDAO
 * - Forward data to manager-dashboard.jsp
 */

package com.esukan.controller;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.esukan.dao.BookingDAO;
import com.esukan.dao.FacilityDAO;
import com.esukan.dao.EquipmentDAO;
import com.esukan.dao.EquipmentRentalDAO;
import java.util.List;
import com.esukan.model.Booking;
import com.esukan.model.EquipmentRental;


@WebServlet("/ManagerDashboardServlet")
public class ManagerDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Temporary dummy data
        BookingDAO bookingDAO = new BookingDAO();
        FacilityDAO facilityDAO = new FacilityDAO();
        EquipmentDAO equipmentDAO = new EquipmentDAO();
        EquipmentRentalDAO equipmentRentalDAO = new EquipmentRentalDAO();
        
        List<Booking> facilityList = bookingDAO.getAllBookings();
        List<EquipmentRental> rentalList = equipmentRentalDAO.getAllRentals();
        System.out.println("Jumlah rekod ditemui: " + facilityList.size());
        request.setAttribute("facilityBookings", facilityList);
        request.setAttribute("equipmentRentals", rentalList);
        request.setAttribute("totalBookings", bookingDAO.getTotalBookings());
        request.setAttribute("totalFacilities", facilityDAO.getTotalFacilities());
        request.setAttribute("totalEquipment", equipmentDAO.getTotalEquipment());
        request.setAttribute("availableEquipment", equipmentDAO.getAvailableEquipment());
        request.getRequestDispatcher("manager-dashboard.jsp").forward(request, response);
    }
}
