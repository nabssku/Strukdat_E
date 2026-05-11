package demo.modul3.task2;

import java.util.*;

public class tiketKAI {

    static Queue<String> antrian = new LinkedList<>();
    static Stack<String> history = new Stack<>();

    static Scanner input = new Scanner(System.in);

    public static void addPassenger() {
        System.out.print("Enter Penumpang Nama: ");
        String name = input.nextLine();

        antrian.add(name);
        System.out.println("Penumpang ditambahkan to antrian.");
    }

    public static void displayQueue() {
        if (antrian.isEmpty()) {
            System.out.println("Antrian Sedang Kosong..");
            return;
        }

        System.out.println("Antrian Sekarang:");
        int i = 1;
        for (String p : antrian) {
            System.out.println(i++ + ". " + p);
        }
    }

    public static void servePassenger() {
        if (antrian.isEmpty()) {
            System.out.println("Tidak Ada yang bisa dilayani, santai dulu.");
            return;
        }

        String served = antrian.poll();
        System.out.println("Melayani Penumpang: " + served);

        history.push(served);
        System.out.println("Transaction Tersimpan.");
    }

    public static void undo() {
        if (history.isEmpty()) {
            System.out.println("Tidak Ada yang bisa DiUndo");
            return;
        }

        String last = history.pop();
        antrian.add(last);

        System.out.println("Undo transaction for Penumpang: " + last);
    }

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n=== KAI Tiket  ===");
            System.out.println("1. Tambah Penumpang");
            System.out.println("2. Tampilkan Antrian");
            System.out.println("3. Layani Penumpang");
            System.out.println("4. Kembali Trakhir Transaction");
            System.out.println("0. Exit");

            System.out.print("Pilih menu: ");
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
