package demo.modul1;

import java.util.Scanner;

// ENUM
enum TicketClass {
    ECONOMY,
    BUSINESS,
    EXECUTIVE
}

// GENERIC PASSENGER
class Passenger<T> {
    String name;
    T identityNumber;

    public Passenger(String name, T identityNumber) {
        this.name = name;
        this.identityNumber = identityNumber;
    }
}

// GENERIC TICKET
class Ticket<T> {
    String bookingCode;
    Passenger<T> passenger;
    TicketClass ticketClass;

    public Ticket(String bookingCode, Passenger<T> passenger, TicketClass ticketClass) {
        this.bookingCode = bookingCode;
        this.passenger = passenger;
        this.ticketClass = ticketClass;
    }
}

// WILDCARD METHOD
class TicketPrinter {
    public static void printTicket(Ticket<?> ticket) {
        System.out.println("\n=== Ticket Information ===");
        System.out.println("Booking Code     : " + ticket.bookingCode);
        System.out.println("Passenger Name   : " + ticket.passenger.name);
        System.out.println("Identity Type    : " + ticket.passenger.identityNumber.getClass().getSimpleName());
        System.out.println("Identity Number  : " + ticket.passenger.identityNumber);
        System.out.println("Ticket Class     : " + ticket.ticketClass);
    }
}

public class task1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== Railway Ticket Booking ===");

        System.out.print("Enter Passenger Name: ");
        String name = input.nextLine();

        System.out.print("Enter Identity Number: ");
        int id = input.nextInt();
        input.nextLine();

        System.out.print("Enter Booking Code: ");
        String code = input.nextLine();

        System.out.println("\nSelect Ticket Class:");
        System.out.println("1. ECONOMY");
        System.out.println("2. BUSINESS");
        System.out.println("3. EXECUTIVE");
        System.out.print("Enter choice: ");
        int choice = input.nextInt();

        TicketClass selectedClass;
        switch (choice) {
            case 1:
                selectedClass = TicketClass.ECONOMY;
                break;
            case 2:
                selectedClass = TicketClass.BUSINESS;
                break;
            case 3:
                selectedClass = TicketClass.EXECUTIVE;
                break;
            default:
                selectedClass = TicketClass.ECONOMY;
        }

        // CREATE OBJECT (GENERIC)
        Passenger<Integer> passenger = new Passenger<>(name, id);
        Ticket<Integer> ticket = new Ticket<>(code, passenger, selectedClass);

        // PRINT (WILDCARD)
        TicketPrinter.printTicket(ticket);
    }
}