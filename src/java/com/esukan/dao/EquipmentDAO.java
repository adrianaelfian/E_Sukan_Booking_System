/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esukan.dao;

/**
 *
 * @author user
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.esukan.model.Booking;
import com.esukan.model.EquipmentRental;

public class EquipmentDAO {
        private Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:derby://localhost:1527/ESukanDB","app","app");
    }
    
    public int getTotalEquipment(){
        int total=0;
        String sql = "SELECT COUNT(*) FROM EQUIPMENT";
        
        try(Connection conn = getConnection();
                PreparedStatement ps=conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            
            if (rs.next()){
                total = rs.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return total;
    }
    
    public int getAvailableEquipment(){
        
        int total =0;
        String sql = "SELECT COUNT(*) FROM EQUIPMENT WHERE EQUIPMENTSTATUS='Available'";
        
        try(Connection conn = getConnection();
                PreparedStatement ps=conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            
            if (rs.next()){
                total = rs.getInt(1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        
        return total;
    }
    public boolean addEquipmentRental(Booking booking) {
    // Perhatikan: RENTALID tiada dalam senarai kolum di bawah
    String sql = "INSERT INTO EQUIPMENTRENTAL (USERID, EQUIPMENTID, RENTALDATE, QUANTITY, RETURNDATE, STATUS) VALUES (?, ?, ?, ?, ?, ?)";
    
    try (Connection conn = getConnection(); 
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        pstmt.setInt(1, booking.getUserId());
        pstmt.setInt(2, booking.getEquipmentId());
        pstmt.setString(3, booking.getRentalDate());
        pstmt.setInt(4, booking.getQuantity());
        pstmt.setString(5, booking.getReturnDate());
        pstmt.setString(6, "Pending"); // Status default
        
        return pstmt.executeUpdate() > 0;
        
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

    public List<EquipmentRental> getAllRentals() {
    List<EquipmentRental> list = new ArrayList<>();
    String sql = "SELECT * FROM EQUIPMENTRENTAL";
    try (Connection conn = getConnection();
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {
        
        while (rs.next()) {
            EquipmentRental r = new EquipmentRental();
            r.setUserId(rs.getInt("USERID"));
            r.setEquipmentId(rs.getInt("EQUIPMENTID"));
            r.setRentalDate(rs.getString("RENTALDATE"));
            r.setQuantity(rs.getInt("QUANTITY"));
            r.setReturnDate(rs.getString("RETURNDATE"));
            r.setStatus(rs.getString("STATUS"));
            // Tambah setter untuk studentName jika perlu
            list.add(r);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
    }
