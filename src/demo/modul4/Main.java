package demo.modul4;

import java.util.Scanner;

/**
 * ============================================================
 * Kelas Main - Program Utama dengan Menu Interaktif
 *
 * Task 1 : Sistem Hierarki Stasiun Kereta Api (General Tree)
 * Task 2 : Sistem Manajemen Pegawai (Binary Search Tree)
 *
 * Mata Kuliah : Struktur Data - Modul 4 (Tree)
 * Lab         : Informatika - Universitas Muhammadiyah Malang
 * ============================================================
 */
public class Main {

    // Scanner dibuat satu, dipakai bersama seluruh program
    static Scanner sc = new Scanner(System.in);

    // Objek global untuk Task 1 dan Task 2
    static StationHierarchy jaringan = new StationHierarchy();
    static EmployeeBST      bst      = new EmployeeBST();

    // ============================================================
    //  MAIN - Menu Utama
    // ============================================================
    public static void main(String[] args) {
        int pilihan;

        do {
            cetakMenuUtama();
            pilihan = bacaInt("Pilih menu");

            switch (pilihan) {
                case 1  -> menuTask1();
                case 2  -> menuTask2();
                case 0  -> System.out.println("\n  Terima kasih! Program selesai.\n");
                default -> System.out.println("\n  [!] Pilihan tidak valid. Coba lagi.\n");
            }

        } while (pilihan != 0);
    }

    // ============================================================
    //  TAMPILAN MENU UTAMA
    // ============================================================
    static void cetakMenuUtama() {
        System.out.println();
        garis('=', 60);
        System.out.println("   MODUL 4 - TREE DATA STRUCTURE");
        System.out.println("   Informatika Laboratory - UMM");
        garis('=', 60);
        System.out.println("   [1] Task 1  -  Hierarki Stasiun (General Tree)");
        System.out.println("   [2] Task 2  -  Manajemen Pegawai (BST)");
        System.out.println("   [0] Keluar");
        garis('-', 60);
    }

    // ============================================================
    //  TASK 1 - MENU HIERARKI STASIUN (General Tree)
    // ============================================================
    static void menuTask1() {

        // Jika pohon masih kosong, minta set root dulu sebelum masuk menu
        if (jaringan.getRoot() == null) {
            System.out.println("\n  [INFO] Pohon stasiun masih kosong.");
            System.out.println("  Silakan tentukan Stasiun Pusat (Root) terlebih dahulu.");
            menuSetRoot();
        }

        int pilihan;
        do {
            cetakMenuTask1();
            pilihan = bacaInt("Pilih menu Task 1");

            switch (pilihan) {
                case 1  -> menuTambahStasiun();
                case 2  -> menuCariStasiun();
                case 3  -> menuHapusStasiun();
                case 4  -> jaringan.preOrderTraversal();
                case 5  -> jaringan.postOrderTraversal();
                case 6  -> jaringan.levelOrderTraversal();
                case 7  -> jaringan.printStats();
                case 8  -> menuSetRoot();
                case 0  -> System.out.println("\n  Kembali ke Menu Utama...\n");
                default -> System.out.println("\n  [!] Pilihan tidak valid.\n");
            }

        } while (pilihan != 0);
    }

    static void cetakMenuTask1() {
        System.out.println();
        garis('=', 60);
        System.out.println("   TASK 1  -  SISTEM HIERARKI STASIUN (General Tree)");
        if (jaringan.getRoot() != null)
            System.out.println("   Root    :  " + jaringan.getRoot());
        garis('=', 60);
        System.out.println("   [1] Tambah Stasiun Baru");
        System.out.println("   [2] Cari Stasiun");
        System.out.println("   [3] Hapus Stasiun");
        System.out.println("   [4] Traversal Pre-Order   (Root -> Anak-anak)");
        System.out.println("   [5] Traversal Post-Order  (Anak-anak -> Root)");
        System.out.println("   [6] Traversal Level-Order (BFS, per Tingkat)");
        System.out.println("   [7] Statistik Pohon  (Total Stasiun & Tinggi)");
        System.out.println("   [8] Ganti Stasiun Pusat (Root)");
        System.out.println("   [0] Kembali ke Menu Utama");
        garis('-', 60);
    }

    // ------ Set Root Stasiun ------
    static void menuSetRoot() {
        System.out.println("\n  === Tetapkan Stasiun Pusat (Root) ===");
        System.out.print("  Kode Stasiun  : "); String kode = sc.nextLine().trim().toUpperCase();
        System.out.print("  Nama Stasiun  : "); String nama = sc.nextLine().trim();
        System.out.print("  Wilayah       : "); String area = sc.nextLine().trim();

        StationNode root = new StationNode(kode, nama, area);
        jaringan.setRoot(root);
        System.out.println("\n  [+] Stasiun pusat ditetapkan : " + root);
    }

    // ------ Tambah Stasiun ------
    static void menuTambahStasiun() {
        System.out.println("\n  === Tambah Stasiun Baru ===");
        System.out.print("  Kode Stasiun Induk  : "); String induk = sc.nextLine().trim().toUpperCase();
        System.out.print("  Kode Stasiun Baru   : "); String kode  = sc.nextLine().trim().toUpperCase();
        System.out.print("  Nama Stasiun Baru   : "); String nama  = sc.nextLine().trim();
        System.out.print("  Wilayah             : "); String area  = sc.nextLine().trim();

        StationNode baru = new StationNode(kode, nama, area);
        jaringan.addStation(induk, baru);
    }

    // ------ Cari Stasiun ------
    static void menuCariStasiun() {
        System.out.println("\n  === Cari Stasiun ===");
        System.out.print("  Masukkan Kode Stasiun : "); String kode = sc.nextLine().trim().toUpperCase();

        StationNode hasil = jaringan.findStation(kode);
        if (hasil != null) {
            System.out.println("\n  [+] Stasiun Ditemukan!");
            System.out.println("      Kode     : " + hasil.stationCode);
            System.out.println("      Nama     : " + hasil.stationName);
            System.out.println("      Wilayah  : " + hasil.region);
            System.out.println("      Induk    : " +
                    (hasil.parent != null ? hasil.parent.stationCode + " - " + hasil.parent.stationName
                            : "(Root / tidak punya induk)"));
            System.out.println("      Jumlah Sub-Stasiun : " + hasil.children.size());
            System.out.println("      Stasiun Daun?      : " + (hasil.isLeaf() ? "Ya" : "Tidak"));
        } else {
            System.out.println("\n  [-] Stasiun dengan kode '" + kode + "' tidak ditemukan.");
        }
    }

    // ------ Hapus Stasiun ------
    static void menuHapusStasiun() {
        System.out.println("\n  === Hapus Stasiun ===");
        System.out.println("  INFO: Anak-anak dari stasiun yang dihapus akan dipindah ke induknya.");
        System.out.print("  Kode Stasiun yang dihapus : "); String kode = sc.nextLine().trim().toUpperCase();

        jaringan.removeStation(kode);
    }

    // ============================================================
    //  TASK 2 - MENU MANAJEMEN PEGAWAI (BST)
    // ============================================================
    static void menuTask2() {
        int pilihan;
        do {
            cetakMenuTask2();
            pilihan = bacaInt("Pilih menu Task 2");

            switch (pilihan) {
                case 1  -> menuTambahPegawai();
                case 2  -> menuCariPegawai();
                case 3  -> menuHapusPegawai();
                case 4  -> bst.inorderTraversal();
                case 5  -> bst.preorderTraversal();
                case 6  -> bst.postorderTraversal();
                case 7  -> tampilMinMax();
                case 0  -> System.out.println("\n  Kembali ke Menu Utama...\n");
                default -> System.out.println("\n  [!] Pilihan tidak valid.\n");
            }

        } while (pilihan != 0);
    }

    static void cetakMenuTask2() {
        System.out.println();
        garis('=', 60);
        System.out.println("   TASK 2  -  MANAJEMEN PEGAWAI KERETA API (BST)");
        garis('=', 60);
        System.out.println("   [1] Tambah Pegawai");
        System.out.println("   [2] Cari Pegawai berdasarkan ID");
        System.out.println("   [3] Hapus Pegawai berdasarkan ID");
        System.out.println("   [4] Traversal Inorder   (Urut ID Kecil ke Besar)");
        System.out.println("   [5] Traversal Preorder  (Root -> Kiri -> Kanan)");
        System.out.println("   [6] Traversal Postorder (Kiri -> Kanan -> Root)");
        System.out.println("   [7] Tampilkan ID Terkecil & Terbesar (Min/Max)");
        System.out.println("   [0] Kembali ke Menu Utama");
        garis('-', 60);
    }

    // ------ Tambah Pegawai ------
    static void menuTambahPegawai() {
        System.out.println("\n  === Tambah Pegawai Baru ===");
        int    id      = bacaInt("ID Pegawai     ");
        System.out.print("  Nama Lengkap  : "); String nama    = sc.nextLine().trim();
        System.out.print("  Departemen    : "); String dept    = sc.nextLine().trim();
        System.out.print("  Jabatan       : "); String jabatan = sc.nextLine().trim();

        bst.insert(new Employee(id, nama, dept, jabatan));
    }

    // ------ Cari Pegawai ------
    static void menuCariPegawai() {
        System.out.println("\n  === Cari Pegawai ===");
        int id = bacaInt("Masukkan ID Pegawai");

        Employee hasil = bst.search(id);
        if (hasil != null) {
            System.out.println("\n  [+] Pegawai Ditemukan!");
            System.out.println("      " + hasil);
        } else {
            System.out.println("\n  [-] Pegawai dengan ID " + id + " tidak ditemukan.");
        }
    }

    // ------ Hapus Pegawai ------
    static void menuHapusPegawai() {
        System.out.println("\n  === Hapus Pegawai ===");
        System.out.println("  INFO: Program otomatis menangani 3 kasus penghapusan:");
        System.out.println("        Kasus 1 - Node Daun (tidak punya anak)");
        System.out.println("        Kasus 2 - Punya 1 anak");
        System.out.println("        Kasus 3 - Punya 2 anak (diganti in-order successor)");
        int id = bacaInt("Masukkan ID Pegawai yang dihapus");

        bst.delete(id);
    }

    // ------ Tampilkan Min & Max ------
    static void tampilMinMax() {
        System.out.println("\n  === ID Terkecil & Terbesar ===");
        Employee min = bst.findMin();
        Employee max = bst.findMax();

        if (min != null) System.out.println("  ID Terkecil : " + min);
        if (max != null) System.out.println("  ID Terbesar : " + max);
    }

    // ============================================================
    //  UTILITAS
    // ============================================================

    /**
     * Membaca input integer dari user.
     * Jika bukan angka, tampilkan pesan error dan minta ulang.
     */
    static int bacaInt(String label) {
        while (true) {
            try {
                System.out.print("  " + label + " : ");
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("  [!] Input harus berupa angka. Coba lagi.");
            }
        }
    }

    /** Mencetak garis pemisah dengan karakter dan panjang tertentu. */
    static void garis(char karakter, int panjang) {
        System.out.println(String.valueOf(karakter).repeat(panjang));
    }
}
