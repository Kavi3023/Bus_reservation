package com.busreservation.service;

import com.busreservation.dao.BusDAO;
import com.busreservation.dao.BookingDAO;
import com.busreservation.dao.PassengerDAO;
import com.busreservation.model.Booking;
import com.busreservation.model.Passenger;

public class BookingService {

    private PassengerDAO pdao = new PassengerDAO();
    private BookingDAO bdao = new BookingDAO();
    private BusDAO busdao = new BusDAO();

    // 🎟️ BOOK TICKET WITH SEAT CHECK
    public void bookTicket(String name, int age, String gender, int busId, String date) {

        // Get total seats of bus
        int totalSeats = busdao.getTotalSeats(busId);

        if (totalSeats == 0) {
            System.out.println("Invalid Bus ID!");
            return;
        }

        // Get already booked seats
        int bookedSeats = bdao.getBookedCount(busId, date);

        int availableSeats = totalSeats - bookedSeats;

        System.out.println("Available Seats: " + availableSeats);

        if (availableSeats <= 0) {
            System.out.println("No seats available for this bus on selected date!");
            return;
        }

        // Add passenger
        Passenger p = new Passenger(name, age, gender);
        int passengerId = pdao.addPassenger(p);

        // Create booking
        Booking b = new Booking(busId, passengerId, date);
        bdao.addBooking(b);

        System.out.println("✅ Ticket Booked Successfully!");
    }

    // 🚌 VIEW ALL BUSES
    public void showBuses() {
        busdao.viewBuses();
    }

    // 📄 VIEW ALL BOOKINGS
    public void showBookings() {
        bdao.viewBookings();
    }

    // ❌ CANCEL BOOKING
    public void cancelBooking(int bookingId) {
        bdao.cancelBooking(bookingId);
    }
}