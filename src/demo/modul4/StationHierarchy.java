package demo.modul4;

import java.util.LinkedList;
import java.util.Queue;

/**
 * ============================================================
 * Kelas StationHierarchy
 * Mengelola seluruh struktur pohon (General Tree) jaringan stasiun.
 * Berisi operasi: tambah, cari, hapus, traversal, dan statistik pohon.
 * ============================================================
 */
public class StationHierarchy {

    // Akar (root) dari pohon, yaitu stasiun utama/pusat
    private StationNode root;

    // ================================================================
    //  SET ROOT - Menentukan stasiun utama
    // ================================================================
    /**
     * Menetapkan stasiun pusat sebagai akar dari seluruh jaringan.
     * @param root Node stasiun yang akan jadi pusat
     */
    public void setRoot(StationNode root) {
        this.root = root;
    }

    public StationNode getRoot() {
        return root;
    }

    // ================================================================
    //  ADD STATION - Menambah stasiun baru
    // ================================================================
    /**
     * Menambahkan stasiun baru di bawah stasiun induk tertentu.
     * Pencarian induk dilakukan berdasarkan kode stasiun.
     *
     * @param parentCode Kode stasiun induk
     * @param newStation Node stasiun baru yang akan ditambahkan
     * @return true jika berhasil, false jika induk tidak ditemukan
     */
    public boolean addStation(String parentCode, StationNode newStation) {
        // Cari stasiun induk berdasarkan kode
        StationNode parent = findStation(parentCode);

        if (parent == null) {
            System.out.println("  [!] Gagal: Stasiun induk dengan kode '" + parentCode + "' tidak ditemukan.");
            return false;
        }

        parent.addChild(newStation); // Tambahkan stasiun baru sebagai anak
        System.out.println("  [+] Stasiun " + newStation + " berhasil ditambahkan di bawah " + parent);
        return true;
    }

    // ================================================================
    //  FIND STATION - Mencari stasiun (Rekursif)
    // ================================================================
    /**
     * Mencari stasiun berdasarkan kode (dimulai dari akar).
     * @param code Kode stasiun yang dicari
     * @return Node stasiun jika ditemukan, null jika tidak ada
     */
    public StationNode findStation(String code) {
        return findStationRec(root, code);
    }

    /**
     * Metode rekursif untuk mencari stasiun di seluruh pohon.
     * Cara kerja: cek node saat ini → cari ke semua anaknya satu per satu.
     */
    private StationNode findStationRec(StationNode current, String code) {
        // Jika node kosong, langsung kembalikan null
        if (current == null) return null;

        // Jika kode node ini cocok, kembalikan node ini
        if (current.stationCode.equalsIgnoreCase(code)) return current;

        // Cari ke setiap anak secara rekursif
        for (StationNode child : current.children) {
            StationNode result = findStationRec(child, code);
            if (result != null) return result; // Kalau ketemu di anak, langsung kembalikan
        }

        return null; // Tidak ditemukan
    }

    // ================================================================
    //  REMOVE STATION - Menghapus stasiun
    // ================================================================
    /**
     * Menghapus stasiun berdasarkan kode.
     * Anak-anak dari stasiun yang dihapus akan dipindahkan ke induknya (grandparent).
     *
     * @param code Kode stasiun yang akan dihapus
     * @return true jika berhasil dihapus
     */
    public boolean removeStation(String code) {
        StationNode target = findStation(code);

        // Validasi: stasiun tidak ditemukan
        if (target == null) {
            System.out.println("  [!] Stasiun dengan kode '" + code + "' tidak ditemukan.");
            return false;
        }

        // Validasi: tidak boleh hapus root
        if (target == root) {
            System.out.println("  [!] Tidak bisa menghapus stasiun pusat (root).");
            return false;
        }

        StationNode parent = target.parent;

        // Pindahkan semua anak ke induk (grandparent), agar tidak hilang
        for (StationNode child : target.children) {
            child.parent = parent;
            parent.children.add(child);
        }

        // Hapus stasiun target dari daftar anak induknya
        parent.removeChild(target);
        System.out.println("  [-] Stasiun " + target + " berhasil dihapus. Anak-anaknya dipindah ke " + parent);
        return true;
    }

    // ================================================================
    //  TRAVERSAL 1: PRE-ORDER (Root → Anak-anak)
    // ================================================================
    /**
     * Traversal Pre-Order: kunjungi node saat ini dulu, baru anak-anaknya.
     * Berguna untuk: menyalin/mencetak struktur pohon dari atas ke bawah.
     */
    public void preOrderTraversal() {
        System.out.println("\n--- Pre-Order Traversal (Root → Anak) ---");
        preOrderRec(root, 0);
    }

    private void preOrderRec(StationNode node, int level) {
        if (node == null) return;

        // Buat indentasi agar terlihat hierarkinya
        String indent = "  ".repeat(level);
        String prefix = (level == 0) ? "ROOT " : "└── ";
        System.out.println(indent + prefix + node);

        // Kunjungi semua anak secara rekursif
        for (StationNode child : node.children) {
            preOrderRec(child, level + 1);
        }
    }

    // ================================================================
    //  TRAVERSAL 2: POST-ORDER (Anak-anak → Root)
    // ================================================================
    /**
     * Traversal Post-Order: kunjungi semua anak dulu, baru node saat ini.
     * Berguna untuk: logika penghapusan (hapus dari bawah ke atas).
     */
    public void postOrderTraversal() {
        System.out.println("\n--- Post-Order Traversal (Anak → Root) ---");
        postOrderRec(root);
    }

    private void postOrderRec(StationNode node) {
        if (node == null) return;

        // Kunjungi semua anak dulu
        for (StationNode child : node.children) {
            postOrderRec(child);
        }

        // Baru cetak node saat ini
        System.out.println("  " + node);
    }

    // ================================================================
    //  TRAVERSAL 3: LEVEL-ORDER / BFS (Level demi Level)
    // ================================================================
    /**
     * Traversal Level-Order (BFS): kunjungi node per tingkatan (level).
     * Berguna untuk: melihat struktur jaringan lapis per lapis.
     * Menggunakan antrian (Queue) agar urutan level terjaga.
     */
    public void levelOrderTraversal() {
        System.out.println("\n--- Level-Order Traversal (BFS - Per Tingkat) ---");
        if (root == null) return;

        Queue<StationNode> antrian = new LinkedList<>();
        antrian.add(root); // Mulai dari root
        int level = 0;

        while (!antrian.isEmpty()) {
            int ukuranLevel = antrian.size(); // Jumlah node di level ini
            System.out.print("  Level " + level + ": ");

            for (int i = 0; i < ukuranLevel; i++) {
                StationNode current = antrian.poll(); // Ambil dari depan antrian
                System.out.print(current.stationCode);
                if (i < ukuranLevel - 1) System.out.print(" | ");

                // Masukkan semua anak ke antrian untuk diproses di level berikutnya
                antrian.addAll(current.children);
            }
            System.out.println();
            level++;
        }
    }

    // ================================================================
    //  STATISTIK POHON
    // ================================================================
    /**
     * Menghitung total jumlah stasiun dalam jaringan.
     */
    public int totalStations() {
        return countNodes(root);
    }

    private int countNodes(StationNode node) {
        if (node == null) return 0;
        int total = 1; // Hitung node ini sendiri
        for (StationNode child : node.children) {
            total += countNodes(child); // Tambahkan hitungan dari semua anak
        }
        return total;
    }

    /**
     * Menghitung tinggi pohon (panjang jalur terpanjang dari root ke daun).
     */
    public int treeHeight() {
        return heightRec(root);
    }

    private int heightRec(StationNode node) {
        if (node == null) return -1;   // Pohon kosong, tinggi = -1
        if (node.isLeaf()) return 0;   // Daun, tinggi = 0

        int maxChildHeight = -1;
        for (StationNode child : node.children) {
            int childHeight = heightRec(child);
            if (childHeight > maxChildHeight) {
                maxChildHeight = childHeight;
            }
        }
        return maxChildHeight + 1; // Tinggi = tinggi anak tertinggi + 1
    }

    /**
     * Mencetak ringkasan statistik pohon.
     */
    public void printStats() {
        System.out.println("\n--- Statistik Jaringan Stasiun ---");
        System.out.println("  Total Stasiun : " + totalStations());
        System.out.println("  Tinggi Pohon  : " + treeHeight() + " tingkat");
    }
}
