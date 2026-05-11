package demo.modul2.task2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

public class ScheduleService {

    private LinkedList<Schedule> scheduleList;

    public ScheduleService() {
        scheduleList = new LinkedList<>();
    }

    public void addScheduleAtStart(Schedule s) {
        scheduleList.addFirst(s);
    }

    public void addScheduleAtEnd(Schedule s) {
        scheduleList.addLast(s);
    }

    public void removeFirstSchedule() {
        if (!scheduleList.isEmpty()) {
            scheduleList.removeFirst();
        }
    }

    public void removeLastSchedule() {
        if (!scheduleList.isEmpty()) {
            scheduleList.removeLast();
        }
    }

    public void searchRoute(String city) {
        for (Schedule s : scheduleList) {
            if (s.getFrom().equalsIgnoreCase(city)
                    || s.getTo().equalsIgnoreCase(city)) {
                s.printSchedule();
            }
        }
    }

    public void showSchedules() {
        Iterator<Schedule> iterator = scheduleList.iterator();
        while (iterator.hasNext()) {
            iterator.next().printSchedule();
        }
    }

    public void removeScheduleById(int id) {
        Iterator<Schedule> iterator = scheduleList.iterator();

        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
            }
        }
    }

    public static void main(String[] args) {

        ScheduleService service = new ScheduleService();
        Scanner input = new Scanner(System.in);
        int menu;

        do {
            System.out.println("\n=== TRAIN SCHEDULE MENU ===");
            System.out.println("1. Tambah di Awal | Prioritas");
            System.out.println("2. Tambah di Akhir");
            System.out.println("3. Hapus Jadwal Pertama");
            System.out.println("4. Hapus Jadwal Terakhir");
            System.out.println("5. Cari Berdasarkan Kota");
            System.out.println("6. Tampilkan Semua");
            System.out.println("7. Hapus Berdasarkan ID");
            System.out.println("0. Keluar");
            System.out.print("Pilih: ");

            menu = input.nextInt();
            input.nextLine();

            switch (menu) {

                case 1:
                case 2:
                    System.out.print("Code: ");
                    String code = input.nextLine();

                    System.out.print("Nama Kereta: ");
                    String name = input.nextLine();

                    System.out.print("Dari: ");
                    String from = input.nextLine();

                    System.out.print("Ke: ");
                    String to = input.nextLine();

                    System.out.print("Jam Berangkat: ");
                    String time = input.nextLine();

                    System.out.print("Harga: ");
                    double price = input.nextDouble();
                    input.nextLine();

                    Schedule newSchedule =
                            new Schedule(code, name, from, to, time, price);

                    if (menu == 1) {
                        service.addScheduleAtStart(newSchedule);
                    } else {
                        service.addScheduleAtEnd(newSchedule);
                    }

                    System.out.println("Jadwal berhasil ditambahkan.");
                    break;

                case 3:
                    service.removeFirstSchedule();
                    System.out.println("Jadwal pertama dihapus.");
                    break;

                case 4:
                    service.removeLastSchedule();
                    System.out.println("Jadwal terakhir dihapus.");
                    break;

                case 5:
                    System.out.print("Masukkan nama kota: ");
                    String city = input.nextLine();
                    service.searchRoute(city);
                    break;

                case 6:
                    service.showSchedules();
                    break;

                case 7:
                    System.out.print("Masukkan ID: ");
                    int id = input.nextInt();
                    input.nextLine();
                    service.removeScheduleById(id);
                    System.out.println("Jika ID ada, jadwal sudah dihapus.");
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
