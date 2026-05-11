package demo.modul1;

import java.util.Scanner;

// enu
enum TicketClass {
    ECONOMY,
    BUSINESS,
    EXECUTIVE
}

class Passenger<T> {
    String name;
    T identityNumber;

    public Passenger(String name, T identityNumber) {
        this.name = name;
        this.identityNumber = identityNumber;
    }
}


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


class TicketPrinter {
    public static void printTicket(Ticket<?> ticket) {
        System.out.println("\n=== Ticket Information ===");
        System.out.println("Kode Booking     : " + ticket.bookingCode);
        System.out.println("Nama Penumpang   : " + ticket.passenger.name);
        System.out.println("Identity Type    : " + ticket.passenger.identityNumber.getClass().getSimpleName());
        System.out.println("Identity Number  : " + ticket.passenger.identityNumber);
        System.out.println("Ticket Class     : " + ticket.ticketClass);
    }
}

public class task1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== Book Ticket Train ===");

        System.out.print("Masukan Passenger Nama: ");
        String name = input.nextLine();

        System.out.print("Masukan Identity Nomor: ");
        String id = input.nextLine();


        System.out.print("Masukan Booking Kode: ");
        String code = input.nextLine();

        System.out.println("\nPilih Ticket Class:");
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


        Passenger<?> passenger = new Passenger<>(name, id);
        Ticket<?> ticket = new Ticket<>(code, passenger, selectedClass);


        TicketPrinter.printTicket(ticket);
    }
}