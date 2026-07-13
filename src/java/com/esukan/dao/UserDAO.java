package com.esukan.dao;

import com.esukan.model.User;
import java.sql.*;

public class UserDAO {
    
    private Connection getConnection() throws SQLException {
        String url = "jdbc:derby://localhost:1527/ESukanDB";
        String username = "app";
        String password = "app";

        return DriverManager.getConnection(url, username, password);
    }

    public boolean registerUser(User user) {
        String sql = "INSERT INTO USERS (FULL_NAME, EMAIL, PHONE_NUMBER, PASSWORD, ROLE) VALUES (?,?,?,?,?)";
        
        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, user.getFullName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhoneNumber());
            ps.setString(4, user.getPassword()); 
            ps.setString(5, user.getRole());     
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public User validateUser(String email, String password, String role) {
        
        User user = null;
        
        String sql = "SELECT * FROM USERS WHERE EMAIL=? AND PASSWORD=? AND ROLE=?";
        
        try (Connection conn = getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, email);
            ps.setString(2, password);
            ps.setString(3, role);
            
            ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    
                    user = new User();
                    
                    user.setId(rs.getInt("ID"));
                    user.setFullName(rs.getString("FULL_NAME"));
                    user.setEmail(rs.getString("EMAIL"));
                    user.setPhoneNumber(rs.getString("PHONE_NUMBER"));
                    user.setPassword(rs.getString("PASSWORD"));
                    user.setRole(rs.getString("ROLE"));
                    
                }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
