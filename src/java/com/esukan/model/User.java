/*
 * MVC: MODEL
 * Represents a user entity in the E-Sukan Booking System.
 * - Store user information
 * - Provide getters and setters
 * - Transfer data between Controller and DAO
 */
package com.esukan.model;

/**
 *
 * @author adriana
 */
public class User {
    private int id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String role;
    private String password;

    // Constructor Kosong
    public User() {}

    
    public User(int id, String fullName, String email, String phoneNumber, String role, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.password = password;
    }

    public User(String fullName, String email, String phoneNumber, String role, String password) {
        this.fullName = fullName;
        this.email =email;
        this.phoneNumber =phoneNumber;
        this.role = role;
        this.password = password;
    }

    // Getter dan Setter 
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
