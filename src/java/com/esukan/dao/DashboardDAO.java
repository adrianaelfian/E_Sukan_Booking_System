/*
 * MVC: MODEL (DAO)
 * Retrieves dashboard statistics from the database.
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
        String url = "jdbc:derby://localhost:1527/esukan";
        String username = "app";
        String password = "app";

        return DriverManager.getConnection(url, username, password);
    }

}
    
