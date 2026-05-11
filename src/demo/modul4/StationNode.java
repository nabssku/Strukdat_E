package demo.modul4;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================
 * Kelas StationNode
 * Merepresentasikan satu stasiun dalam struktur pohon umum (General Tree).
 * Setiap stasiun bisa memiliki banyak anak (sub-stasiun).
 * ============================================================
 */
public class StationNode {

    // --- ATRIBUT ---
    String stationCode;           // Kode unik stasiun (contoh: "GMR", "SBI")
    String stationName;           // Nama lengkap stasiun
    String region;                // Wilayah/daerah stasiun
    List<StationNode> children;   // Daftar sub-stasiun di bawah stasiun ini
    StationNode parent;           // Referensi ke stasiun induk (atas)

    // --- KONSTRUKTOR ---
    /**
     * Membuat objek stasiun baru.
     * @param stationCode Kode unik stasiun
     * @param stationName Nama stasiun
     * @param region      Wilayah stasiun
     */
    public StationNode(String stationCode, String stationName, String region) {
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.region      = region;
        this.children    = new ArrayList<>(); // Daftar anak dimulai kosong
        this.parent      = null;              // Belum punya induk saat pertama dibuat
    }

    // --- METODE ---

    /**
     * Menambahkan sub-stasiun ke stasiun ini.
     * @param child Node stasiun yang akan dijadikan anak
     */
    public void addChild(StationNode child) {
        child.parent = this;       // Atur induk dari si anak
        this.children.add(child);  // Masukkan ke daftar anak
    }

    /**
     * Menghapus sub-stasiun dari stasiun ini.
     * @param child Node stasiun yang ingin dihapus
     */
    public void removeChild(StationNode child) {
        this.children.remove(child);
        child.parent = null; // Putuskan hubungan dengan induk
    }

    /**
     * Mengecek apakah stasiun ini adalah daun (tidak punya anak).
     * @return true jika tidak punya sub-stasiun
     */
    public boolean isLeaf() {
        return this.children.isEmpty();
    }

    /**
     * Menampilkan informasi stasiun dalam bentuk teks.
     */
    @Override
    public String toString() {
        return "[" + stationCode + "] " + stationName + " (" + region + ")";
    }
}
