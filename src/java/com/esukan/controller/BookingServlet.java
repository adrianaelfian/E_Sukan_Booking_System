/*
 * MVC: CONTROLLER
 * Handles facility and equipment booking requests.
 * - Receive booking information
 * - Validate booking
 * - Call BookingDAO
 * - Redirect to booking confirmation page
 */
package com.esukan.controller;

import com.esukan.dao.BookingDAO;
import com.esukan.dao.EquipmentRentalDAO;
import com.esukan.model.Booking;
import com.esukan.model.User;
import com.esukan.model.EquipmentRental;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "BookingServlet", urlPatterns = {"/BookingServlet"})
public class BookingServlet extends HttpServlet {

    private BookingDAO bookingDAO;
    
    // Initialize the BookingDAO object when the servlet starts.
    @Override
    public void init() {
        bookingDAO = new BookingDAO();
    }
    
    /*
    * Handles HTTP GET requests.
    * Used for viewing, editing, approving,
    * rejecting and cancelling bookings.
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        // Process user action based on the requested operation.
        if ("view".equals(action)) {
            viewBookings(request, response);
        } else if ("approve".equals(action)) {
            approveBooking(request, response);
        } else if ("reject".equals(action)) {
            rejectBooking(request, response);
        } else if ("cancel".equals(action)) {
            String type = request.getParameter("type");
            if ("booking".equals(type)) {
                showCancelConfirmation(request, response, "booking");
            } else if ("equipment".equals(type)) {
                showCancelConfirmation(request, response, "rental");
            }
        } else if ("edit".equals(action)) { // panggil bila user klik butang Edit
            if (request.getParameter("bookingId") != null) {
                showEditBookingForm(request, response);
            } else if (request.getParameter("rentalId") != null) {
                showEditRentalForm(request, response);
            }
        }else {
            response.sendRedirect("BookingServlet?action=view");
        }
    }

    /*
    * Handles HTTP POST requests.
    * Used for creating, updating and
    * cancelling bookings or rentals.
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Determine the submitted form action.
        String action = request.getParameter("action");
        
        if ("bookFacility".equals(action)) {
            bookFacility(request, response);
        } else if ("bookEquipment".equals(action)) {
            bookEquipment(request, response);
        } else if ("updateBooking".equals(action)) {
            updateBooking(request, response);
        } else if ("updateRental".equals(action)) { // TAMBAH INI
            updateRental(request, response);
        } else if ("cancel".equals(action)) {
            if (request.getParameter("bookingId") != null && !request.getParameter("bookingId").isEmpty()) {
                cancelBooking(request, response);
                System.out.println("Action cancel telah dikesan!");
            } else if (request.getParameter("rentalId") != null && !request.getParameter("rentalId").isEmpty()) {
                cancelRental(request, response);
            }
        } else {
            response.sendRedirect("StudentDashboardServlet");
        }
    }

    /*
    * Processes a new sport facility booking.
    * Retrieves form data, creates a Booking object,
    * saves it using BookingDAO and redirects the user.
    */
    private void bookFacility(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Verify that the user is logged in.
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null){
            response.sendRedirect("login.jsp");
            return;
        }
        
        User user = (User) session.getAttribute("user");
        int facilityId = Integer.parseInt(request.getParameter("facilityId"));
        String date = request.getParameter("date");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        int playerNumber = Integer.parseInt(request.getParameter("numPlayers"));

        Booking booking = new Booking();
        booking.setUserId(user.getId());
        booking.setFacilityId(facilityId);
        booking.setDate(date);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);
        booking.setPlayerNumber(playerNumber);

        // Save the booking record into the database.
        boolean success = bookingDAO.addBooking(booking);

        if (success) {
            response.sendRedirect("BookingServlet?action=view");
        } else {
            request.setAttribute("message", "Booking failed. Please try again.");
            request.setAttribute("messageType", "error");
            request.getRequestDispatcher("booking-facility.jsp").forward(request, response);
        }
    }
    
    
    /*
    * Processes a new equipment rental request.
    * Creates an EquipmentRental object and
    * stores it in the database.
    */
    private void bookEquipment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    if (session == null || session.getAttribute("user") == null){
        response.sendRedirect("login.jsp");
        return;
    }
    
    User user = (User) session.getAttribute("user");
    
    // 1. Ambil data dari borang
    int equipmentId = Integer.parseInt(request.getParameter("equipmentId"));
    String equipmentName = "";
    switch (equipmentId) {
        case 1: equipmentName = "Badminton Racket - Yonex"; break;
        case 2: equipmentName = "Badminton Racket - ProTech"; break;
        case 3: equipmentName = "Shuttlecock (Tube)"; break;
        case 4: equipmentName = "Futsal Ball - Size 4"; break;
        case 5: equipmentName = "Futsal Ball - Size 5"; break;
        case 6: equipmentName = "Basketball"; break;
        default: equipmentName = "Unknown Equipment";
    }
    int quantity = Integer.parseInt(request.getParameter("quantity"));
    String rentalDate = request.getParameter("rentalDate");
    String returnDate = request.getParameter("returnDate");

    // 2. Bina objek Rental/Equipment
    EquipmentRental rental = new EquipmentRental();
    rental.setUserId(user.getId());
    rental.setEquipmentId(equipmentId);
    rental.setQuantity(quantity);
    rental.setRentalDate(rentalDate);
    rental.setReturnDate(returnDate);
    rental.setStatus("Pending");
    rental.setEquipmentName(equipmentName);

    // 3. Simpan ke database menggunakan DAO
    EquipmentRentalDAO rentalDAO = new EquipmentRentalDAO();
    boolean success = rentalDAO.addRental(rental); // Pastikan anda ada rentalDAO

    // 4. Redirect ke halaman yang betul
    if (success) {
        response.sendRedirect("BookingServlet?action=view"); // Tukar ke halaman view
    } else {
        request.setAttribute("message", "Rental failed. Please try again.");
        request.getRequestDispatcher("book-equipment.jsp").forward(request, response);
    }
}

    /*
     * Retrieves booking and rental records.
     * Displays different data based on the
     * user's role (Manager or Student).
     */
    private void viewBookings(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    HttpSession session = request.getSession(false);
    
    if (session == null || session.getAttribute("user") == null){
        response.sendRedirect("login.jsp");
        return;
    }
    
    User user = (User) session.getAttribute("user");
    
    // Load all booking records for managers.
    if ("Manager".equalsIgnoreCase(user.getRole())) {
        // PERUBAHAN DI SINI:
        // Gunakan pemboleh ubah 'bookings' dan 'rentals' yang telah anda isytiharkan di atas
        List<Booking> bookings = bookingDAO.getAllBookings();
        request.setAttribute("facilityBookings", bookings); // 'bookings' adalah List<Booking>
        
        List<EquipmentRental> rentals = bookingDAO.getAllEquipmentRentals();
        request.setAttribute("equipmentRentals", rentals); // 'rentals' adalah List<EquipmentRental>
        
        request.getRequestDispatcher("viewBookings_manager.jsp").forward(request, response);
    } else {
        List<Booking> bookings = bookingDAO.getBookingsByUser(user.getId());
        request.setAttribute("facilityBookings", bookings);
        EquipmentRentalDAO rentalDAO = new EquipmentRentalDAO(); 
        List<EquipmentRental> rentals = rentalDAO.getRentalsByUser(user.getId());
        request.setAttribute("equipmentRentals", rentals);
        request.getRequestDispatcher("viewBookings_student.jsp").forward(request, response);
    }
}
    /*
     * Approves a pending booking or
     * equipment rental request.
     */
    private void approveBooking(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
        String bId = request.getParameter("bookingId");
        String rId = request.getParameter("rentalId");

        if (bId != null && !bId.isEmpty()) {
            int bookingId = Integer.parseInt(bId);
            bookingDAO.updateBookingStatus(bookingId, "Approved");
        } else if (rId != null && !rId.isEmpty()) {
            int rentalId = Integer.parseInt(rId);
            bookingDAO.updateEquipmentRentalStatus(rentalId, "Approved"); // Pastikan method ini wujud di BookingDAO
        }
        response.sendRedirect("ManagerDashboardServlet");
    }

    /*
     * Approves a pending booking or
     * equipment rental request.
     */
    private void rejectBooking(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
        String bId = request.getParameter("bookingId");
        String rId = request.getParameter("rentalId");

        if (bId != null && !bId.isEmpty()) {
           int bookingId = Integer.parseInt(bId);
           bookingDAO.updateBookingStatus(bookingId, "Rejected");
        } else if (rId != null && !rId.isEmpty()) {
            int rentalId = Integer.parseInt(rId);
            bookingDAO.updateEquipmentRentalStatus(rentalId, "Rejected"); // Pastikan method ini wujud di BookingDAO
        }
        response.sendRedirect("ManagerDashboardServlet");
    }
    
    /*
     * Deletes the selected booking record
     * from the database.
     */
    private void cancelBooking(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        bookingDAO.deleteBooking(bookingId);
        response.sendRedirect("StudentDashboardServlet");
    }
    
    /*
     * Deletes the selected equipment
     * rental record from the database.
     */
    private void cancelRental(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int rentalId = Integer.parseInt(request.getParameter("rentalId"));
        bookingDAO.deleteRental(rentalId);
        response.sendRedirect("StudentDashboardServlet");
    }

    /*
     * Updates an existing booking record
     * with the latest information.
     */
    private void updateBooking(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
        String date = request.getParameter("date");
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        int playerNumber = Integer.parseInt(request.getParameter("playerNumber"));
    
        // Panggil method baru yang kita kemaskini di atas
        boolean success = bookingDAO.updateBooking(bookingId, date, startTime, endTime, playerNumber);
    
        if (success) {
            System.out.println("Update berjaya!");
        } else {
            System.out.println("Update GAGAL! Sila check SQL di DAO.");
        }
        response.sendRedirect("StudentDashboardServlet");
    }
    
    /*
     * Updates an existing equipment
     * rental record.
     */
    private void updateRental(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int rentalId = Integer.parseInt(request.getParameter("rentalId"));
        
        String newRentalDate = request.getParameter("rentalDate");
        String newReturnDate = request.getParameter("returnDate");
        String newStatus = request.getParameter("status");
        boolean success = bookingDAO.updateRental(rentalId, newRentalDate,newReturnDate, newStatus);
        response.sendRedirect("StudentDashboardServlet");
    }
    
    // Method untuk PAPAR (Dipanggil dalam doGet)
    private void showEditBookingForm(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int bookingId = Integer.parseInt(request.getParameter("bookingId"));
    
        // Anda perlu ambil data asal dahulu
        Booking booking = bookingDAO.getBookingById(bookingId); 
    
        // Hantar data ke JSP
        request.setAttribute("booking", booking);
        request.getRequestDispatcher("edit-booking.jsp").forward(request, response);
    }
    
    // Method untuk PAPAR (Dipanggil dalam doGet)
    private void showEditRentalForm(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        int rentalId = Integer.parseInt(request.getParameter("rentalId"));
    
        // Anda perlu ambil data asal dahulu
        EquipmentRental rental = bookingDAO.getEquipmentRentalById(rentalId); 
    
        // Hantar data ke JSP
        request.setAttribute("rental", rental);
        request.getRequestDispatcher("edit-booking.jsp").forward(request, response);
    }
    
    // Method baru untuk menyediakan data sebelum ke JSP
    private void showCancelConfirmation(HttpServletRequest request, HttpServletResponse response, String type) 
        throws ServletException, IOException {
    
        if ("booking".equals(type)) {
            int bId = Integer.parseInt(request.getParameter("bookingId"));
            request.setAttribute("item", bookingDAO.getBookingById(bId)); 
        } else {
            int rId = Integer.parseInt(request.getParameter("rentalId"));
            request.setAttribute("item", bookingDAO.getEquipmentRentalById(rId));;
        }
        request.setAttribute("type", type);
        request.getRequestDispatcher("delete-booking.jsp").forward(request, response);
    }
    
}