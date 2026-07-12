package com.esukan.dao;

import com.esukan.model.Booking;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    
    private Connection getConnection() throws SQLException {
        String url = "jdbc:derby://localhost:1527/esukan";
        String username = "app";
        String password = "app";
        return DriverManager.getConnection(url, username, password);
    }
    
    // CREATE - Add booking
    public boolean addBooking(Booking booking) {
        String sql = "INSERT INTO FACILITYBOOKING (userID, facilityID, date, startTime, endTime, playerNumber, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, booking.getUserId());
            pstmt.setInt(2, booking.getFacilityId());
            pstmt.setString(3, booking.getBookingDate());
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
    
    // READ - Get all bookings
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        String sql = "SELECT FB.BOOKINGID, U.FULLNAME, F.FACILITYNAME, FB.DATE, FB.STATUS " +
                     "FROM FACILITYBOOKING FB " +
                     "JOIN USERS U ON FB.USERID = U.ID " +
                     "JOIN FACILITY F ON FB.FACILITYID = F.FACILITYID " +
                     "ORDER BY FB.BOOKINGID DESC";       
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("BOOKINGID"));
                booking.setStudentName(rs.getString("FULLNAME"));
                booking.setFacilityName(rs.getString("FACILITYNAME"));
                booking.setBookingDate(rs.getString("DATE"));
                booking.setStatus(rs.getString("STATUS"));
                bookings.add(booking);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bookings;
    }
    
    // READ - Get bookings by user
    public List<Booking> getBookingsByUser(int userId) {
        List<Booking> bookings = new ArrayList<>();
        String sql =  "SELECT FB.BOOKINGID, " +
                      "F.FACILITYNAME, " +
                      "FB.DATE, " +
                      "FB.STARTTIME, " +
                      "FB.ENDTIME, " +
                      "FB.STATUS " +
                      "FROM FACILITYBOOKING FB " +
                      "JOIN FACILITY F ON FB.FACILITYID = F.FACILITYID " +
                      "WHERE FB.USERID = ? " +
                      "ORDER BY FB.BOOKINGID DESC";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("BOOKINGID"));
                booking.setFacilityName(rs.getString("FACILITYNAME"));
                booking.setBookingDate(rs.getString("DATE"));
                booking.setStartTime(rs.getString("STARTTIME"));
                booking.setEndTime(rs.getString("ENDTIME"));
                booking.setStatus(rs.getString("STATUS"));

                bookings.add(booking);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bookings;
    }
    
    // UPDATE - Update booking status
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
    
    // DELETE - Cancel booking
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
    // DASHBOARD - Total Bookings
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
}
