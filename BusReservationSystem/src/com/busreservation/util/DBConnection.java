package com.busreservation.util;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBConnection {
	public static Connection getConnection() throws SQLException{
       String url="jdbc:mysql://localhost:3306/busdb";
       String username="root";
       String password="AQWEdfg@234#56yh";
       
       return DriverManager.getConnection(url,username,password);
}
}
