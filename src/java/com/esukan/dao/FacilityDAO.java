/*
 * MVC: MODEL (DAO)
 * Retrieves facility information from the database.
 */
package com.esukan.dao;

/**
 *
 * @author user
 */
import java.sql.*;
public class FacilityDAO {
    
    private Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:derby://localhost:1527/ESukanDB","app","app");
    }
    
    public int getTotalFacilities(){
        int total=0;
        String sql = "SELECT COUNT(*) FROM FACILITY";
        
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
    
    

