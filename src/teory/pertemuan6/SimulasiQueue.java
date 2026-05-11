package teory.pertemuan6;

import java.util.LinkedList;
import java.util.Queue;

public class SimulasiQueue {

    public static void main(String[] args) {

        // 3 antrian
        Queue<Integer> cc1 = new LinkedList<>();
        Queue<Integer> cc2 = new LinkedList<>();
        Queue<Integer> cc3 = new LinkedList<>();

        // ================= CC1 =================
        System.out.println("=== CC1 ===");
        enqueue(cc1, 11001);
        enqueue(cc1, 11011);

        dequeue(cc1); // Layani

        enqueue(cc1, 11123);
        enqueue(cc1, 11015);

        dequeue(cc1); // Layani

        enqueue(cc1, 11331);

        printQueue(cc1);

        // ================= CC2 =================
        System.out.println("\n=== CC2 ===");
        enqueue(cc2, 11011);

        dequeue(cc2); // Layani

        enqueue(cc2, 11321);
        enqueue(cc2, 11250);
        enqueue(cc2, 11890);
        enqueue(cc2, 11765);

        dequeue(cc2); // Layani

        printQueue(cc2);

        // ================= CC3 =================
        System.out.println("\n=== CC3 ===");
        enqueue(cc3, 11543);
        enqueue(cc3, 11632);

        dequeue(cc3); // Layani
        dequeue(cc3); // Layani

        enqueue(cc3, 11387);
        enqueue(cc3, 11289);

        dequeue(cc3); // Layani
        printQueue(cc3);
    }

    // enqueue = masuk
    public static void enqueue(Queue<Integer> q, int data) {
        q.add(data);
        System.out.println("Masuk: " + data);
    }

    // dequeue = layani
    public static void dequeue(Queue<Integer> q) {
        if (!q.isEmpty()) {
            int data = q.poll();
            System.out.println("Layani: " + data);
        } else {
            System.out.println("Antrian kosong, gak ada yang bisa dilayani");
        }
    }

    public static void printQueue(Queue<Integer> q) {
        System.out.println("Sisa Antrian: " + q);
    }
}
