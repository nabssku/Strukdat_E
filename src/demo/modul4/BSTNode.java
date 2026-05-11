package demo.modul4;

/**
 * ============================================================
 * Kelas BSTNode
 * Merepresentasikan satu simpul (node) dalam Binary Search Tree.
 * Setiap node menyimpan data pegawai dan referensi ke kiri & kanan.
 * ============================================================
 */
public class BSTNode {

    Employee data;   // Data pegawai yang disimpan di node ini
    BSTNode  left;   // Anak kiri  → ID lebih KECIL
    BSTNode  right;  // Anak kanan → ID lebih BESAR

    /**
     * Membuat node baru dengan data pegawai.
     * Awalnya tidak punya anak (left & right = null).
     */
    public BSTNode(Employee data) {
        this.data  = data;
        this.left  = null;
        this.right = null;
    }
}
