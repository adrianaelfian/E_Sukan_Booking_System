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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DashboardDAO {
     private Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/esukan_db";
        String username = "root";
        String password = "";

        return DriverManager.getConnection(url, username, password);
    }

}
    
