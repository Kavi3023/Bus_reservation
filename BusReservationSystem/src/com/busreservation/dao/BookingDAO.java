package com.busreservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.busreservation.model.Booking;
import com.busreservation.util.DBConnection;

public class BookingDAO {

    // Insert booking
    public void addBooking(Booking b) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO booking(bus_id, passenger_id, travel_date) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, b.getBusId());
            ps.setInt(2, b.getPassengerId());
            java.sql.Date sqlDate = java.sql.Date.valueOf(b.getDate());
            ps.setDate(3, sqlDate);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // View all bookings
    public void viewBookings() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            String query = "SELECT b.booking_id, p.name, p.age, p.gender, b.bus_id, b.travel_date " +
                           "FROM booking b JOIN passenger p " +
                           "ON b.passenger_id = p.passenger_id";

            ResultSet rs = st.executeQuery(query);

            System.out.println("\n--- Booking Details ---");

            while (rs.next()) {
                System.out.println(
                    "Booking ID: " + rs.getInt("booking_id") +
                    " | Name: " + rs.getString("name") +
                    " | Age: " + rs.getInt("age") +
                    " | Gender: " + rs.getString("gender") +
                    " | Bus ID: " + rs.getInt("bus_id") +
                    " | Date: " + rs.getString("travel_date")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Cancel booking
    public void cancelBooking(int bookingId) {
        try {
            Connection con = DBConnection.getConnection();

            String query = "DELETE FROM booking WHERE booking_id = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, bookingId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Booking Cancelled Successfully!");
            } else {
                System.out.println("Invalid Booking ID! No booking found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int getBookedCount(int busId, String date) {
        int count = 0;

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT COUNT(*) FROM booking WHERE bus_id = ? AND travel_date = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, busId);

            // 🔥 IMPORTANT FIX (convert String → Date)
            java.sql.Date sqlDate = java.sql.Date.valueOf(date);
            ps.setDate(2, sqlDate);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
}
