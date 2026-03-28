package com.busreservation.util;

import java.util.Scanner;
import com.busreservation.service.BookingService;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BookingService service = new BookingService();

        while (true) {
            System.out.println("\n===== Bus Reservation System =====");
            System.out.println("1. View Buses");
            System.out.println("2. Book Ticket");
            System.out.println("3. View Bookings");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Exit");

            System.out.print("Enter Choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    service.showBuses();
                    break;

                case 2:
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();

                    sc.nextLine();
                    System.out.print("Enter Gender: ");
                    String gender = sc.nextLine();

                    System.out.print("Enter Bus ID: ");
                    int busId = sc.nextInt();

                    sc.nextLine();
                    System.out.print("Enter Travel Date (YYYY-MM-DD): ");
                    String date = sc.nextLine();

                    service.bookTicket(name, age, gender, busId, date);
                    break;

                case 3:
                    service.showBookings();
                    break;

                case 4:
                    System.out.print("Enter Booking ID to cancel: ");
                    int bookingId = sc.nextInt();
                    service.cancelBooking(bookingId);
                    break;

                case 5:
                    System.out.println("Thank you!");
                    System.exit(0);
            }
        }
    }
}

