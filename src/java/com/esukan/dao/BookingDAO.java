

package com.esukan.dao;

import com.esukan.model.Booking;
import com.esukan.model.EquipmentRental;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    
    private Connection getConnection() throws SQLException {
        String url = "jdbc:derby://localhost:1527/ESukanDB";
        String username = "app";
        String password = "app";
        return DriverManager.getConnection(url, username, password);
    }
    
    // CREATE - Add booking facility
    public boolean addBooking(Booking booking) {
        String sql = "INSERT INTO FACILITYBOOKING (userID, facilityID, date, startTime, endTime, playerNumber, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, booking.getUserId());
            pstmt.setInt(2, booking.getFacilityId());
            pstmt.setString(3, booking.getDate());
            pstmt.setString(4, booking.getStartTime());
            pstmt.setString(5, booking.getEndTime());
            pstmt.setInt(6, booking.getPlayerNumber());
            pstmt.setString(7, "Pending");
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // CREATE - Add rental equipment
    public boolean addEquipmentRental(EquipmentRental rental) {
    // Sila pastikan nama kolum (RENTALDATE, RETURNDATE) sama dengan database anda
        String sql = "INSERT INTO EQUIPMENTRENTAL (userID, equipmentId, quantity, RENTALDATE, RETURNDATE, STATUS, EQUPMENTNAME) VALUES (?, ?, ?, ?, ?, ?,?)";
    
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
            pstmt.setInt(1, rental.getUserId());
            pstmt.setInt(2, rental.getEquipmentId());
            pstmt.setInt(3, rental.getQuantity());
            pstmt.setString(4, rental.getRentalDate()); // Gunakan field ini untuk rentalDate
            pstmt.setString(5, rental.getReturnDate());  // Tambah field ini di Booking model nanti
            pstmt.setString(6, "Pending");
            pstmt.setString(7,rental.getEquipmentName());
        
            return pstmt.executeUpdate() > 0;
        
        } catch (SQLException e) {
            e.printStackTrace(); 
            return false;
        }
    }
    
    // READ - Get all bookings Facility
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT FB.BOOKINGID, U.FULL_NAME, F.FACILITYNAME, FB.DATE, FB.STATUS " +
             "FROM FACILITYBOOKING FB " +
             "LEFT JOIN USERS U ON FB.USERID = U.ID " +
             "LEFT JOIN FACILITY F ON FB.FACILITYID = F.FACILITYID " +
             "ORDER BY FB.BOOKINGID DESC";  
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("BOOKINGID"));
                booking.setStudentName(rs.getString("FULL_NAME"));
                booking.setFacilityName(rs.getString("FACILITYNAME"));
                booking.setDate(rs.getString("DATE"));
                booking.setStatus(rs.getString("STATUS"));
                bookings.add(booking);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bookings;
    }
    
    // READ - Get bookings facility by user
    public List<Booking> getBookingsByUser(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String sql =  "SELECT FB.BOOKINGID, F.FACILITYNAME, FB.DATE, FB.STARTTIME, FB.ENDTIME, FB.PLAYERNUMBER, FB.STATUS " +
                 "FROM FACILITYBOOKING FB " +
                 "LEFT JOIN FACILITY F ON FB.FACILITYID = F.FACILITYID " +
                 "WHERE FB.USERID = ? " +
                 "ORDER BY FB.BOOKINGID DESC";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("BOOKINGID"));
                booking.setFacilityName(rs.getString("FACILITYNAME")); // Mengambil dari table FACILITY
                booking.setDate(rs.getString("DATE"));
                booking.setStartTime(rs.getString("STARTTIME"));       // Pastikan kolum ini wujud
                booking.setEndTime(rs.getString("ENDTIME"));           // Pastikan kolum ini wujud
                booking.setPlayerNumber(rs.getInt("PLAYERNUMBER"));    // Pastikan kolum ini wujud
                booking.setStatus(rs.getString("STATUS"));

                bookings.add(booking);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bookings;
    }
    
    // READ - Get rental equipment by user
    public List<EquipmentRental> getRentalsByUser(int userId) {
        List<EquipmentRental> bookings = new ArrayList<>();
        String sql =  "SELECT ER.RENTALID, E.EQUIPMENTNAME, ER.RENTALDATE,ER.QUANTITY, ER.RETURNDATE, ER.STATUS" +
                 "FROM EQUIPMENTRENTAL ER " +
                 "LEFT JOIN EQUIPMENT E ON ER.EQUIPMENTID = E.EQUIPMENTID " +
                 "WHERE ER.USERID = ? " +
                 "ORDER BY ER.RENTALID DESC";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                EquipmentRental rental = new EquipmentRental();
                rental.setRentalId(rs.getInt("RENTALID"));
                rental.setEquipmentName(rs.getString("EQUIPMENTNAME")); // Mengambil dari table FACILITY
                rental.setRentalDate(rs.getString("RENTALDATE"));
                rental.setQuantity(rs.getInt("QUANTITY"));       // Pastikan kolum ini wujud
                rental.setReturnDate(rs.getString("RETURNDATE"));           // Pastikan kolum ini wujud
                rental.setStatus(rs.getString("STATUS"));

                bookings.add(rental);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bookings;
    }
    
    // UPDATE - Update booking facility status
    public boolean updateBookingStatus(int bookingId, String status) {
        String sql = "UPDATE FACILITYBOOKING SET status = ? WHERE bookingID = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, status);
            pstmt.setInt(2, bookingId);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // UPDATE - Update booking facility
    public boolean updateBooking(int bookingId, String date, String startTime, String endTime, int playerNumber) {
        String sql = "UPDATE FACILITYBOOKING SET DATE = ?, STARTTIME = ?, ENDTIME = ?, PLAYERNUMBER = ? WHERE BOOKINGID = ?";
    
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
            // Tetapkan nilai parameter mengikut turutan ? dalam SQL
            pstmt.setString(1, date);
            pstmt.setString(2, startTime);
            pstmt.setString(3, endTime);
            pstmt.setInt(4, playerNumber);
            pstmt.setInt(5, bookingId);
            // executeUpdate() mengembalikan jumlah baris yang terkesan (1 jika berjaya)
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // UPDATE - Update booking facility
    public boolean updateRental(int rentalId, String newRentalDate,String newReturnDate, String newStatus) {
        String sql = "UPDATE EQUIPMENTRENTAL SET RENTALDATE = ?,RETURNDATE = ?, STATUS = ? WHERE RENTALID = ?";
    
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
            // Tetapkan nilai parameter mengikut turutan ? dalam SQL
            pstmt.setString(1, newRentalDate);
            pstmt.setString(2, newReturnDate);
            pstmt.setString(3, newStatus);
            pstmt.setInt(4, rentalId);
        
            // executeUpdate() mengembalikan jumlah baris yang terkesan (1 jika berjaya)
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // DELETE - Cancel booking facility
    public boolean deleteBooking(int bookingId) {
        String sql = "DELETE FROM FACILITYBOOKING WHERE bookingID = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, bookingId);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // DELETE - Cancel rental equipment
    public boolean deleteRental(int rentalId) {
        String sql = "DELETE FROM EQUIPMENTRENTAL WHERE RENTALID = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, rentalId);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // DASHBOARD - Total Bookings facility
    public int getTotalBookings() {
        
        int total=0;
        String sql="SELECT COUNT(*) FROM FACILITYBOOKING";
        
        try (Connection conn = getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql);
                ResultSet rs= pstmt.executeQuery()) {
            
            if (rs.next()) {
                    total = rs.getInt(1);
                }
            } catch (SQLException e){
                e.printStackTrace();
        }
        return total;
    }
    
    // DASHBOARD - Total Rentals equipment
    public int getTotalRentals() {
        
        int total=0;
        String sql="SELECT COUNT(*) FROM EQUIPMENTRENTAL";
        
        try (Connection conn = getConnection();
                PreparedStatement pstmt=conn.prepareStatement(sql);
                ResultSet rs= pstmt.executeQuery()) {
            
            if (rs.next()) {
                    total = rs.getInt(1);
                }
            } catch (SQLException e){
                e.printStackTrace();
        }
        return total;
    }
    
    public Booking getBookingById(int bookingId) {

    Booking booking = null;

    String sql =
        "SELECT FB.BOOKINGID, FB.FACILITYID, F.FACILITYNAME, " +
        "FB.DATE, FB.STARTTIME, FB.ENDTIME, FB.PLAYERNUMBER, FB.STATUS " +
        "FROM FACILITYBOOKING FB " +
        "LEFT JOIN FACILITY F ON FB.FACILITYID = F.FACILITYID " +
        "WHERE FB.BOOKINGID = ?";

    try(Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql)){

        pstmt.setInt(1, bookingId);

        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){

            booking = new Booking();

            booking.setBookingId(rs.getInt("BOOKINGID"));
            booking.setFacilityId(rs.getInt("FACILITYID"));
            booking.setFacilityName(rs.getString("FACILITYNAME"));
            booking.setDate(rs.getString("DATE"));
            booking.setStartTime(rs.getString("STARTTIME"));
            booking.setEndTime(rs.getString("ENDTIME"));
            booking.setPlayerNumber(rs.getInt("PLAYERNUMBER"));
            booking.setStatus(rs.getString("STATUS"));

        }

    }catch(SQLException e){
        e.printStackTrace();
    }

    return booking;
}
    public EquipmentRental getEquipmentRentalById(int rentalId) {
    EquipmentRental rental = null;
    String sql = "SELECT ER.RENTALID, ER.EQUIPMENTID, E.EQUIPMENTNAME, " +
                 "ER.RENTALDATE, ER.RETURNDATE, ER.QUANTITY, ER.STATUS " +
                 "FROM EQUIPMENTRENTAL ER " +
                 "LEFT JOIN EQUIPMENT E ON ER.EQUIPMENTID = E.EQUIPMENTID " +
                 "WHERE ER.RENTALID = ?";

    try (Connection conn = getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setInt(1, rentalId);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            rental = new EquipmentRental();
            rental.setRentalId(rs.getInt("RENTALID"));
            rental.setEquipmentId(rs.getInt("EQUIPMENTID"));
            rental.setEquipmentName(rs.getString("EQUIPMENTNAME"));
            rental.setRentalDate(rs.getString("RENTALDATE"));
            rental.setReturnDate(rs.getString("RETURNDATE"));
            rental.setQuantity(rs.getInt("QUANTITY"));
            rental.setStatus(rs.getString("STATUS"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return rental;
}
    // READ - Get all equipment rentals for manager view
    public List<EquipmentRental> getAllEquipmentRentals() {
        List<EquipmentRental> rentals = new ArrayList<>();
        // Pastikan nama kolum dan table (EQUIPMENTRENTAL, EQUIPMENT, USERS) sama dengan database anda
        String sql = "SELECT ER.RENTALID, U.FULL_NAME, E.EQUIPMENTNAME, ER.RENTALDATE,ER.QUANTITY, ER.RETURNDATE, ER.STATUS " +
                     "FROM EQUIPMENTRENTAL ER " +
                     "LEFT JOIN USERS U ON ER.USERID = U.ID " +
                     "LEFT JOIN EQUIPMENT E ON ER.EQUIPMENTID = E.EQUIPMENTID " +
                     "ORDER BY ER.RENTALID DESC";
    
        try (Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
        
            while (rs.next()) {
                EquipmentRental rental = new EquipmentRental();
                rental.setRentalId(rs.getInt("RENTALID"));
                rental.setStudentName(rs.getString("FULL_NAME")); // Pastikan model EquipmentRental mempunyai setStudentName
                rental.setEquipmentName(rs.getString("EQUIPMENTNAME"));
                rental.setRentalDate(rs.getString("RENTALDATE"));
                rental.setReturnDate(rs.getString("RETURNDATE"));
                rental.setQuantity(rs.getInt("QUANTITY"));
                rental.setStatus(rs.getString("STATUS"));
            
                rentals.add(rental);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }
    // UPDATE - Update equipment rental status
    public boolean updateEquipmentRentalStatus(int rentalId, String status) {
        String sql = "UPDATE EQUIPMENTRENTAL SET status = ? WHERE RENTALID = ?";
    
        try (Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
            pstmt.setString(1, status);
            pstmt.setInt(2, rentalId);
        
            return pstmt.executeUpdate() > 0;
        
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // DASHBOARD - Mengira jumlah kemudahan (Facilities)
    public int getTotalFacilities() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM FACILITY";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    // DASHBOARD - Mengira jumlah peralatan (Equipment)
    public int getTotalEquipment() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM EQUIPMENT";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }

    // DASHBOARD - Mengira peralatan yang tersedia (Available Equipment)
    // Andaikan status 'Available' disimpan dalam kolum STATUS
    public int getAvailableEquipment() {
        int total = 0;
        String sql = "SELECT COUNT(*) FROM EQUIPMENT WHERE STATUS = 'Available'";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return total;
    }
    
}