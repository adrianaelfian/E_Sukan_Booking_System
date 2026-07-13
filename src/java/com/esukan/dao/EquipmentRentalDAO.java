package com.esukan.dao;

import com.esukan.model.EquipmentRental;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentRentalDAO {
    
    private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/ESukanDB";
        String username = "root";
        String password = ""; // Change to your MySQL password
        return DriverManager.getConnection(url, username, password);
    }
    
    // CREATE - Add equipment rental
    public boolean addRental(EquipmentRental rental) {
        String sql = "INSERT INTO EquipmentBooking (userID, equipmentID, rentalDate, quantity, duration, status) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, rental.getUserId());
            pstmt.setInt(2, rental.getEquipmentId());
            pstmt.setString(3, rental.getRentalDate());
            pstmt.setInt(4, rental.getQuantity());
            pstmt.setInt(5, rental.getDuration());
            pstmt.setString(6, "Pending");
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // READ - Get all rentals
    public List<EquipmentRental> getAllRentals() {
        List<EquipmentRental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM EquipmentBooking ORDER BY rentalID DESC";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                EquipmentRental rental = new EquipmentRental();
                rental.setRentalId(rs.getInt("rentalID"));
                rental.setUserId(rs.getInt("userID"));
                rental.setEquipmentId(rs.getInt("equipmentID"));
                rental.setRentalDate(rs.getString("rentalDate"));
                rental.setQuantity(rs.getInt("quantity"));
                rental.setDuration(rs.getInt("duration"));
                rental.setStatus(rs.getString("status"));
                rentals.add(rental);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }
    
    // READ - Get rentals by user
    public List<EquipmentRental> getRentalsByUser(int userId) {
        List<EquipmentRental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM EquipmentBooking WHERE userID = ? ORDER BY rentalID DESC";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                EquipmentRental rental = new EquipmentRental();
                rental.setRentalId(rs.getInt("rentalID"));
                rental.setUserId(rs.getInt("userID"));
                rental.setEquipmentId(rs.getInt("equipmentID"));
                rental.setRentalDate(rs.getString("rentalDate"));
                rental.setQuantity(rs.getInt("quantity"));
                rental.setDuration(rs.getInt("duration"));
                rental.setStatus(rs.getString("status"));
                rentals.add(rental);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }
    
    // UPDATE - Update rental status
    public boolean updateRentalStatus(int rentalId, String status) {
        String sql = "UPDATE EquipmentBooking SET status = ? WHERE rentalID = ?";
        
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
    
    // DELETE - Cancel rental
    public boolean deleteRental(int rentalId) {
        String sql = "DELETE FROM EquipmentBooking WHERE rentalID = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, rentalId);
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
