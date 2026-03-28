package com.busreservation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.busreservation.model.Passenger;
import com.busreservation.util.DBConnection;

public class PassengerDAO {

    // Insert passenger and return generated ID
    public int addPassenger(Passenger p) {
        int passengerId = 0;

        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO passenger(name, age, gender) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);

            ps.setString(1, p.getName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getGender());

            ps.executeUpdate();

            // Get generated ID
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                passengerId = rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return passengerId;
    }
}
