package com.esukan.model;

public class Booking {
    private int bookingId;
    private int userId;
    private int facilityId;
    private String date;
    private String startTime;
    private String endTime;
    private int playerNumber;
    private String status;
    private int rentalId;
    private int equipmentId;
    private int quantity;
    private String rentalDate;
    private String returnDate;
    private String studentName;
    private String facilityName;
    private String equipmentName;
    private String equipmentStatus;
    
    public Booking() {
    }
    
    public Booking(int bookingId, int userId, int facilityId, String date, 
                   String startTime, String endTime, int playerNumber, String status,int rentalId, int equipmentId
                   ,int quantity, String rentalDate, String returnDate, String equipmentStatus) 
                   {
        this.bookingId = bookingId;
        this.userId = userId;
        this.facilityId = facilityId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.playerNumber = playerNumber;
        this.status = status;
        this.rentalId= rentalId;
        this.equipmentId = equipmentId;
        this.quantity = quantity;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.equipmentStatus =equipmentStatus;
    }
    
    public int getBookingId() {
        return bookingId;
    }
    
    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getFacilityId() {
        return facilityId;
    }
    
    public void setFacilityId(int facilityId) {
        this.facilityId = facilityId;
    }
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }
    
    public String getStartTime() {
        return startTime;
    }
    
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    public String getEndTime() {
        return endTime;
    }
    
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    public int getPlayerNumber() {
        return playerNumber;
    }
    
    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getStudentName(){
        return studentName;
    }
    
    public void setStudentName(String studentName){
        this.studentName = studentName;
    }
    
    public String getFacilityName(){
        return facilityName;
    }
    
    public void setFacilityName(String facilityName){
        this.facilityName = facilityName;
    }
    
    // Tambah setter untuk Equipment
    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setEquipmentStatus(String equipmentStatus) {
        this.equipmentStatus = equipmentStatus;
    }

    public String getEquipmentStatus() {
        return equipmentStatus;
    }
    
    public String getRentalDate() { 
        return rentalDate; 
    }
    public void setRentalDate(String rentalDate) { 
        this.rentalDate = rentalDate; 
    }
    
    public String getReturnDate() { 
        return returnDate; 
    }
    public void setReturnDate(String returnDate) { 
        this.returnDate = returnDate; 
    }

}
