package com.busreservation.model;

public class Booking {
	 private int busId;
	    private int passengerId;
	    private String date;

	    public Booking(int busId, int passengerId, String date) {
	        this.busId = busId;
	        this.passengerId = passengerId;
	        this.date = date;
	    }

	    public int getBusId() { return busId; }
	    public int getPassengerId() { return passengerId; }
	    public String getDate() { return date; }
}


