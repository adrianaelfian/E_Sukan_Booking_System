package com.esukan.dao;

import com.esukan.model.Booking;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {
    
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/esukan_db";
        String username = "root";
        String password = "";
        return DriverManager.getConnection(url, username, password);
    }
    
    // CREATE - Add booking
    public boolean addBooking(Booking booking) {
        String sql = "INSERT INTO FacilityBooking (userID, facilityID, date, startTime, endTime, playerNumber, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
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
        String sql = "SELECT * FROM FacilityBooking ORDER BY bookingID DESC";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("bookingID"));
                booking.setUserId(rs.getInt("userID"));
                booking.setFacilityId(rs.getInt("facilityID"));
                booking.setBookingDate(rs.getString("date"));
                booking.setStartTime(rs.getString("startTime"));
                booking.setEndTime(rs.getString("endTime"));
                booking.setPlayerNumber(rs.getInt("playerNumber"));
                booking.setStatus(rs.getString("status"));
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
        String sql = "SELECT * FROM FacilityBooking WHERE userID = ? ORDER BY bookingID DESC";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("bookingID"));
                booking.setUserId(rs.getInt("userID"));
                booking.setFacilityId(rs.getInt("facilityID"));
                booking.setBookingDate(rs.getString("date"));
                booking.setStartTime(rs.getString("startTime"));
                booking.setEndTime(rs.getString("endTime"));
                booking.setPlayerNumber(rs.getInt("playerNumber"));
                booking.setStatus(rs.getString("status"));
                bookings.add(booking);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return bookings;
    }
    
    // UPDATE - Update booking status
    public boolean updateBookingStatus(int bookingId, String status) {
        String sql = "UPDATE FacilityBooking SET status = ? WHERE bookingID = ?";
        
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
        String sql = "DELETE FROM FacilityBooking WHERE bookingID = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, bookingId);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
