package demo.modul4;

/**
 * ============================================================
 * Kelas Employee
 * Merepresentasikan data seorang pegawai kereta api.
 * Mengimplementasikan Comparable agar bisa dibandingkan berdasarkan ID.
 * ============================================================
 */
public class Employee implements Comparable<Employee> {

    // --- ATRIBUT ---
    int    employeeId;   // ID unik pegawai (digunakan sebagai kunci BST)
    String name;         // Nama lengkap pegawai
    String department;   // Divisi/departemen pegawai
    String position;     // Jabatan pegawai

    // --- KONSTRUKTOR ---
    /**
     * Membuat objek pegawai baru.
     * @param employeeId ID unik pegawai
     * @param name       Nama lengkap
     * @param department Departemen
     * @param position   Jabatan
     */
    public Employee(int employeeId, String name, String department, String position) {
        this.employeeId = employeeId;
        this.name       = name;
        this.department = department;
        this.position   = position;
    }

    /**
     * Membandingkan dua pegawai berdasarkan ID-nya.
     * Dibutuhkan agar BST tahu mana yang lebih kecil/besar.
     * Negatif = this lebih kecil, Positif = this lebih besar, 0 = sama
     */
    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.employeeId, other.employeeId);
    }

    /**
     * Menampilkan informasi pegawai dalam bentuk teks.
     */
    @Override
    public String toString() {
        return String.format("ID: %4d | %-20s | %-15s | %s",
                employeeId, name, department, position);
    }
}
