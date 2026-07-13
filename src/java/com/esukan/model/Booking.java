package com.esukan.model;

public class Booking {
    private int bookingId;
    private int userId;
    private int facilityId;
    private String bookingDate;
    private String startTime;
    private String endTime;
    private int playerNumber;
    private String status;
    private String studentName;
    private String facilityName;
    
    public Booking() {
    }
    
    public Booking(int bookingId, int userId, int facilityId, String bookingDate, 
                   String startTime, String endTime, int playerNumber, String status) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.facilityId = facilityId;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.playerNumber = playerNumber;
        this.status = status;
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
    
    public String getBookingDate() {
        return bookingDate;
    }
    
    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
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

}
