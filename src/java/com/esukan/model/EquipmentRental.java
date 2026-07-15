package com.esukan.model;

public class EquipmentRental {
    private int rentalId;
    private int userId;
    private int equipmentId;
    private String equipmentName; // Ditambah untuk paparan join table
    private String rentalDate;
    private String returnDate;    // Ditambah untuk padanan dengan DAO
    private int quantity;
    private String status;
    private String studentName;
    
    public EquipmentRental() {}
    
    // GETTERS AND SETTERS
    public int getRentalId() { return rentalId; }
    public void setRentalId(int rentalId) { this.rentalId = rentalId; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public int getEquipmentId() { return equipmentId; }
    public void setEquipmentId(int equipmentId) { this.equipmentId = equipmentId; }
    
    public String getEquipmentName() { return equipmentName; }
    public void setEquipmentName(String equipmentName) { this.equipmentName = equipmentName; }
    
    public String getRentalDate() { return rentalDate; }
    public void setRentalDate(String rentalDate) { this.rentalDate = rentalDate; }
    
    public String getReturnDate() { return returnDate; }
    public void setReturnDate(String returnDate) { this.returnDate = returnDate; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public void setStudentName(String studentName) {this.studentName = studentName;}

    public String getStudentName() {
        return studentName;
    }
}