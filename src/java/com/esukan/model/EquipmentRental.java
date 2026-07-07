package com.esukan.model;

public class EquipmentRental {
    private int rentalId;
    private int userId;
    private int equipmentId;
    private String rentalDate;
    private int quantity;
    private int duration;
    private String status;
    
    public EquipmentRental() {}
    
    public EquipmentRental(int rentalId, int userId, int equipmentId, String rentalDate, 
                           int quantity, int duration, String status) {
        this.rentalId = rentalId;
        this.userId = userId;
        this.equipmentId = equipmentId;
        this.rentalDate = rentalDate;
        this.quantity = quantity;
        this.duration = duration;
        this.status = status;
    }
    
    public int getRentalId() { return rentalId; }
    public void setRentalId(int rentalId) { this.rentalId = rentalId; }
    
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    
    public int getEquipmentId() { return equipmentId; }
    public void setEquipmentId(int equipmentId) { this.equipmentId = equipmentId; }
    
    public String getRentalDate() { return rentalDate; }
    public void setRentalDate(String rentalDate) { this.rentalDate = rentalDate; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
