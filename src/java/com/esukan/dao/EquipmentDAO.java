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
    }
