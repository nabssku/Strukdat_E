package codelab.modul1;

public class main {
    public static void main(String[] args) {

        codelab<String> ticket1 = new codelab<>("KA-001", "Andi");
        ticket1.displayTicket();

        codelab<Integer> ticket2 = new codelab<>(1002, "Budi");
        ticket2.displayTicket();
    }
}
