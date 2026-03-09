package codelab.modul3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class codelab {
    public static void main(String[] args) {

        // Init Fifo
        Queue<String> serviceQueue = new LinkedList<>();

        // Add Penumpg
        serviceQueue.add("Nabil");
        serviceQueue.add("Clara");
        serviceQueue.add("Bila");

        System.out.println("Initial Service Queue:");
        System.out.println(serviceQueue);

        // served
        String servedPassenger = serviceQueue.poll();
        System.out.println("Serving passenger: " + servedPassenger);
        System.out.println("Queue after serving:");
        System.out.println(serviceQueue);

        // cek penumpang lanjutan e
        String nextPassenger = serviceQueue.peek();
        System.out.println("Next passenger to serve: " + nextPassenger);
        System.out.println();



        // init lifo
        Stack<String> transactionHistory = new Stack<>();

        // add transaksi
        transactionHistory.push("Transaction-1");
        transactionHistory.push("Transaction-2");
        transactionHistory.push("Transaction-3");

        System.out.println("Transaction History:");
        System.out.println(transactionHistory);

        // undo transaksi trakhir
        String lastTransaction = transactionHistory.pop();

        System.out.println("Undo last transaction: " + lastTransaction);

        System.out.println("Transaction History after undo:");
        System.out.println(transactionHistory);

        // cek transaksi trakhir tanpa delete
        String topTransaction = transactionHistory.peek();

        System.out.println("Current top transaction: " + topTransaction);

        //cek apakah stack ada yg kosong
        if (transactionHistory.isEmpty()) {
            System.out.println("No transaction history.");
        } else {
            System.out.println("Transaction history is not empty. Size: " +
                    transactionHistory.size());
        }
    }
}
