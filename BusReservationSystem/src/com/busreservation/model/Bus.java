package com.busreservation.model;

public class Bus {
	private int busId;
    private String busName;
    private int totalSeats;

    public Bus(int busId, String busName, int totalSeats) {
        this.busId = busId;
        this.busName = busName;
        this.totalSeats = totalSeats;
    }

    public int getBusId() { return busId; }
    public String getBusName() { return busName; }
    public int getTotalSeats() { return totalSeats; }
}


