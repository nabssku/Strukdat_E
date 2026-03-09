package codelab.modul1;

public class codelab<T> {

    private T bookingCode;
    private String passengerName;

    public codelab(T bookingCode, String passengerName) {
        this.bookingCode = bookingCode;
        this.passengerName = passengerName;
    }

    public T getBookingCode() {
        return bookingCode;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void displayTicket() {
        System.out.println("=== Railway Ticket Information ===");
        System.out.println("Booking Code    : " + bookingCode);
        System.out.println("Passenger Name  : " + passengerName);
        System.out.println("Booking Code Type: " + bookingCode.getClass().getSimpleName());
        System.out.println();
    }
}
