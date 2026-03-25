package teory.pertemuan3;

public class main {

    public static void main(String[] args) {

        SinglyLinkedList<String> kota = new SinglyLinkedList<>();

        kota.addFirst("Malang");
        kota.addLast("Surabaya");
        kota.addLast("Jakarta");

        System.out.println("Singly Linked List:");
        kota.printList();

        System.out.println("Elemen index 1: " + kota.get(1));

        kota.removeFirst();
        kota.printList();

        kota.removeLast();
        kota.printList();


        System.out.println("\nDoubly Linked List:");

        DoublyLinkedList<String> kota2 = new DoublyLinkedList<>();

        kota2.addFirst("Malang");
        kota2.addLast("Surabaya");
        kota2.addLast("Jakarta");

        kota2.printList();

        System.out.println("Elemen index 2: " + kota2.get(2));

        kota2.removeFirst();
        kota2.printList();

        kota2.removeLast();
        kota2.printList();
    }
}