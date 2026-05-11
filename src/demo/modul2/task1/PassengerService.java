package demo.modul2.task1;

import java.util.ArrayList;
import java.util.Scanner;

public class PassengerService {

    private ArrayList<Passenger> passengerList;

    public PassengerService() {
        passengerList = new ArrayList<>();
    }


    public void registerPassenger(Passenger passenger) {
        passengerList.add(passenger);
    }


    public Passenger getPassengerAt(int index) {
        if (index < 0 || index >= passengerList.size()) {
            System.out.println("Index tidak valid.");
            return null;
        }
        return passengerList.get(index);
    }

    // Cari berdasarkan nama (linear search)
    public void findPassengerByName(String name) {
        boolean found = false;
        int index = 0;
        for (Passenger p : passengerList) {
            if (p.getFullName().equalsIgnoreCase(name)) {
                p.showDetail(index);
                found = true;
            }
            index++;
        }

        if (!found) {
            System.out.println("Data tidak ditemukan.");
        }
    }

    // Update berdasarkan index
    public void editPassenger(int index, String name, String email, String phone, int age) {
        Passenger p = getPassengerAt(index);
        if (p != null) {
            p.updateData(name, email, phone, age);
        }
    }

    // Hapus berdasarkan ID
    public void deleteById(int id) {
        passengerList.removeIf(p -> p.getId() == id);
    }

    // Tampilkan semua
    public void showAllPassengers() {
        if (passengerList.isEmpty()) {
            System.out.println("Belum ada penumpang terdaftar.");
            return;
        }
        int index = 0;
        for (Passenger p : passengerList) {
            p.showDetail(index);
            index++;
        }
    }

    // MAIN
    public static void main(String[] args) {

        PassengerService service = new PassengerService();
        Scanner input = new Scanner(System.in);
        int menu;

        do {
            System.out.println("\n=== PASSENGER MENU ===");
            System.out.println("1. Tambah Passenger");
            System.out.println("2. Tampilkan Semua");
            System.out.println("3. Cari Berdasarkan Nama");
            System.out.println("4. Update Berdasarkan Index");
            System.out.println("5. Hapus Berdasarkan ID");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");

            menu = input.nextInt();
            input.nextLine();

            switch (menu) {

                case 1:
                    System.out.print("Nama: ");
                    String name = input.nextLine();

                    System.out.print("Email: ");
                    String email = input.nextLine();

                    System.out.print("Phone: ");
                    String phone = input.nextLine();

                    System.out.print("Age: ");
                    int age = input.nextInt();
                    input.nextLine();

                    service.registerPassenger(
                            new Passenger(name, email, phone, age));
                    System.out.println("Passenger berhasil ditambahkan.");
                    break;

                case 2:
                    service.showAllPassengers();
                    break;

                case 3:
                    System.out.print("Masukkan nama yang dicari: ");
                    String cari = input.nextLine();
                    service.findPassengerByName(cari);
                    break;

                case 4:
                    System.out.print("Index yang mau diupdate: ");
                    int index = input.nextInt();
                    input.nextLine();

                    System.out.print("Nama baru: ");
                    String newName = input.nextLine();

                    System.out.print("Email baru: ");
                    String newEmail = input.nextLine();

                    System.out.print("Phone baru: ");
                    String newPhone = input.nextLine();

                    System.out.print("Age baru: ");
                    int newAge = input.nextInt();
                    input.nextLine();

                    service.editPassenger(index, newName, newEmail, newPhone, newAge);
                    System.out.println("Data berhasil diupdate.");
                    break;

                case 5:
                    System.out.print("Masukkan ID yang ingin dihapus: ");
                    int id = input.nextInt();
                    input.nextLine();
                    service.deleteById(id);
                    System.out.println("Jika ID ada, data sudah dihapus.");
                    break;

                case 0:
                    System.out.println("Program selesai.");
                    break;

                default:
                    System.out.println("Menu tidak tersedia.");
            }

        } while (menu != 0);
    }
}
