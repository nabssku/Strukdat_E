package teory.pertemuan3;

public class SinglyLinkedList<E> {

    class Node {
        E data;
        Node next;

        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size = 0;

    // tambah di depan
    public void addFirst(E data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }

        size++;
    }

    // tambah di belakang
    public void addLast(E data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    // hapus pertama
    public void removeFirst() {
        if (head == null) return;

        head = head.next;

        if (head == null) tail = null;

        size--;
    }

    // hapus terakhir
    public void removeLast() {
        if (head == null) return;

        if (head == tail) {
            head = tail = null;
        } else {
            Node current = head;

            while (current.next != tail) {
                current = current.next;
            }

            current.next = null;
            tail = current;
        }

        size--;
    }

    // ambil elemen ke N
    public E get(int index) {
        if (index < 0 || index >= size) return null;

        Node current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current.data;
    }

    // tampilkan list
    public void printList() {
        Node current = head;

        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }

        System.out.println("null");
    }
}
