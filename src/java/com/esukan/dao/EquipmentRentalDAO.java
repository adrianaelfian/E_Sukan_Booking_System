/*
 * MVC: MODEL (DAO)
 * Handles equipment rental transactions.
 */
package com.esukan.dao;

import com.esukan.model.EquipmentRental;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipmentRentalDAO {
    
    private Connection getConnection() throws SQLException {
        String url = "jdbc:derby://localhost:1527/ESukanDB";
        String username = "app";
        String password = "app"; // Change to your MySQL password
        return DriverManager.getConnection(url, username, password);
    }
    
    // CREATE - Add equipment rental
    public boolean addRental(EquipmentRental rental) {
        String sql = "INSERT INTO EQUIPMENTRENTAL (\"USERID\", EQUIPMENTID, RENTALDATE, QUANTITY, RETURNDATE, STATUS, EQUIPMENTNAME) VALUES (?, ?, ?, ?, ?, ?,?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, rental.getUserId());
            pstmt.setInt(2, rental.getEquipmentId());
            pstmt.setString(3, rental.getRentalDate());
            pstmt.setInt(4, rental.getQuantity());
            pstmt.setString(5, rental.getReturnDate());
            pstmt.setString(6, "Pending");
            pstmt.setString(7, rental.getEquipmentName());
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // READ - Get all rentals
    public List<EquipmentRental> getAllRentals() {
        List<EquipmentRental> rentals = new ArrayList<>();
        String sql = "SELECT ER.*, U.FULL_NAME FROM EQUIPMENTRENTAL ER " +
                 "JOIN USERS U ON ER.USERID = U.ID ORDER BY ER.RENTALID DESC";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                EquipmentRental rental = new EquipmentRental();
                rental.setRentalId(rs.getInt("RENTALID"));
                rental.setUserId(rs.getInt("USERID"));
                rental.setEquipmentId(rs.getInt("EQUIPMENTID"));
                rental.setStudentName(rs.getString("FULL_NAME"));
                rental.setEquipmentName(rs.getString("EQUIPMENTNAME"));
                rental.setRentalDate(rs.getString("RENTALDATE"));
                rental.setQuantity(rs.getInt("QUANTITY"));
                rental.setReturnDate(rs.getString("RETURNDATE"));
                rental.setStatus(rs.getString("STATUS"));
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
        
        String sql = "SELECT * FROM EQUIPMENTRENTAL WHERE USERID = ? ORDER BY RENTALID DESC";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                EquipmentRental rental = new EquipmentRental();
                rental.setRentalId(rs.getInt("RENTALID"));
                rental.setUserId(rs.getInt("USERID"));
                rental.setEquipmentId(rs.getInt("EQUIPMENTID"));
                rental.setEquipmentName(rs.getString("EQUIPMENTNAME"));
                rental.setRentalDate(rs.getString("RENTALDATE"));
                rental.setQuantity(rs.getInt("QUANTITY"));
                rental.setReturnDate(rs.getString("RETURNDATE"));
                rental.setStatus(rs.getString("STATUS"));
                rentals.add(rental);
            }
            System.out.println("DEBUG: Jumlah rental ditemui untuk user " + userId + " ialah " + rentals.size());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }
    
    // UPDATE - Update rental status
    public boolean updateRentalStatus(int rentalId, String equipmentStatus) {
        String sql = "UPDATE EQUIPMENTRENTAL SET STATUS = ? WHERE RENTALID = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, equipmentStatus);
            pstmt.setInt(2, rentalId);
            
            return pstmt.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // DELETE - Cancel rental
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
}
