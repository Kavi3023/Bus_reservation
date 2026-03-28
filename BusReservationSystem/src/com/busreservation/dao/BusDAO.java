package com.busreservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.busreservation.util.DBConnection;

public class BusDAO {

    // 🚌 VIEW ALL BUSES
    public void viewBuses() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();

            String query = "SELECT * FROM bus";
            ResultSet rs = st.executeQuery(query);

            System.out.println("\n--- Available Buses ---");

            boolean hasData = false;

            while (rs.next()) {
                hasData = true;

                int id = rs.getInt("bus_id");
                String name = rs.getString("bus_name");
                int seats = rs.getInt("total_seats");

                System.out.println("Bus ID: " + id +
                                   " | Name: " + name +
                                   " | Total Seats: " + seats);
            }

            if (!hasData) {
                System.out.println("No buses available!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 🎯 GET TOTAL SEATS OF A BUS
    public int getTotalSeats(int busId) {
        int seats = 0;

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT total_seats FROM bus WHERE bus_id = ?";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, busId);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                seats = rs.getInt("total_seats");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return seats;
    }
}