package demo.modul3.task2;

import java.util.*;

public class RailwaySystem {

    static Queue<String> queue = new LinkedList<>();
    static Stack<String> history = new Stack<>();

    static Scanner input = new Scanner(System.in);

    public static void addPassenger() {
        System.out.print("Enter passenger name: ");
        String name = input.nextLine();

        queue.add(name);
        System.out.println("Passenger added to queue.");
    }

    public static void displayQueue() {
        if (queue.isEmpty()) {
            System.out.println("Antrian Sedang Kosong..");
            return;
        }

        System.out.println("Current Queue:");
        int i = 1;
        for (String p : queue) {
            System.out.println(i++ + ". " + p);
        }
    }

    public static void servePassenger() {
        if (queue.isEmpty()) {
            System.out.println("Tidak Ada yang bisa dilayani, santai dulu.");
            return;
        }

        String served = queue.poll();
        System.out.println("Serving passenger: " + served);

        history.push(served);
        System.out.println("Transaction saved.");
    }

    public static void undo() {
        if (history.isEmpty()) {
            System.out.println("Tidak Ada yang bisa DiUndo");
            return;
        }

        String last = history.pop();
        queue.add(last);

        System.out.println("Undo transaction for passenger: " + last);
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n=== Railway Ticket Service ===");
            System.out.println("1. Add Passenger");
            System.out.println("2. Display Queue");
            System.out.println("3. Serve Passenger");
            System.out.println("4. Undo Last Transaction");
            System.out.println("0. Exit");

            System.out.print("Choose menu: ");
            choice = input.nextInt();
            input.nextLine(); // clear buffer

            switch (choice) {
                case 1: addPassenger(); break;
                case 2: displayQueue(); break;
                case 3: servePassenger(); break;
                case 4: undo(); break;
            }

        } while (choice != 0);
    }
}
