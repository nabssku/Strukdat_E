package demo.modul4;

/**
 * ============================================================
 * Kelas EmployeeBST
 * Mengelola data pegawai menggunakan struktur Binary Search Tree (BST).
 *
 * Aturan BST:
 *   - ID lebih KECIL dari root → masuk ke KIRI
 *   - ID lebih BESAR dari root → masuk ke KANAN
 *
 * Operasi: Insert, Search, Delete, Traversal (Inorder/Preorder/Postorder),
 *          FindMin, FindMax
 * ============================================================
 */
public class EmployeeBST {

    private BSTNode root; // Akar dari pohon BST

    // ================================================================
    //  INSERT - Menambah pegawai baru
    // ================================================================
    /**
     * Menambahkan pegawai baru ke dalam BST.
     * Posisi ditentukan otomatis berdasarkan ID pegawai.
     * @param emp Data pegawai yang akan ditambahkan
     */
    public void insert(Employee emp) {
        root = insertRec(root, emp);
    }

    /**
     * Metode rekursif untuk menyisipkan node baru.
     * Cara kerja:
     *   1. Jika posisi kosong → buat node baru di sini
     *   2. Jika ID baru < ID node saat ini → masuk ke kiri
     *   3. Jika ID baru > ID node saat ini → masuk ke kanan
     *   4. Jika ID sama → abaikan (tidak boleh duplikat)
     */
    private BSTNode insertRec(BSTNode node, Employee emp) {
        // Posisi kosong → ini tempatnya
        if (node == null) {
            System.out.println("  [+] Pegawai ditambahkan: " + emp);
            return new BSTNode(emp);
        }

        int perbandingan = emp.compareTo(node.data);

        if (perbandingan < 0) {
            // ID lebih kecil → masuk ke subtree KIRI
            node.left = insertRec(node.left, emp);
        } else if (perbandingan > 0) {
            // ID lebih besar → masuk ke subtree KANAN
            node.right = insertRec(node.right, emp);
        } else {
            // ID sama → tidak diizinkan (BST tidak boleh duplikat)
            System.out.println("  [!] ID " + emp.employeeId + " sudah ada. Tidak dapat menambahkan duplikat.");
        }

        return node;
    }

    // ================================================================
    //  SEARCH - Mencari pegawai berdasarkan ID
    // ================================================================
    /**
     * Mencari pegawai berdasarkan ID.
     * @param id ID pegawai yang dicari
     * @return Objek Employee jika ditemukan, null jika tidak ada
     */
    public Employee search(int id) {
        BSTNode result = searchRec(root, id);
        return (result != null) ? result.data : null;
    }

    /**
     * Metode rekursif pencarian di BST.
     * Cara kerja:
     *   - Jika node kosong → tidak ditemukan
     *   - Jika ID cocok → ketemu!
     *   - Jika ID target < ID node → cari ke kiri
     *   - Jika ID target > ID node → cari ke kanan
     */
    private BSTNode searchRec(BSTNode node, int id) {
        if (node == null) return null;           // Tidak ditemukan
        if (node.data.employeeId == id) return node; // Ketemu!

        if (id < node.data.employeeId) {
            return searchRec(node.left, id);   // Cari ke kiri
        } else {
            return searchRec(node.right, id);  // Cari ke kanan
        }
    }

    // ================================================================
    //  DELETE - Menghapus pegawai berdasarkan ID
    // ================================================================
    /**
     * Menghapus pegawai dengan ID tertentu dari BST.
     * @param id ID pegawai yang akan dihapus
     */
    public void delete(int id) {
        if (search(id) == null) {
            System.out.println("  [!] Pegawai dengan ID " + id + " tidak ditemukan.");
            return;
        }
        root = deleteRec(root, id);
        System.out.println("  [-] Pegawai ID " + id + " berhasil dihapus dari sistem.");
    }

    /**
     * Metode rekursif penghapusan dengan 3 kasus:
     *
     * KASUS 1 - Node adalah DAUN (tidak punya anak)
     *   → Langsung hapus (kembalikan null)
     *
     * KASUS 2 - Node punya SATU ANAK
     *   → Gantikan node ini dengan anaknya
     *
     * KASUS 3 - Node punya DUA ANAK
     *   → Cari penerus in-order (nilai terkecil di subtree KANAN)
     *   → Salin nilainya ke node ini, lalu hapus penerus tersebut
     */
    private BSTNode deleteRec(BSTNode node, int id) {
        if (node == null) return null;

        if (id < node.data.employeeId) {
            // Target ada di subtree kiri
            node.left = deleteRec(node.left, id);

        } else if (id > node.data.employeeId) {
            // Target ada di subtree kanan
            node.right = deleteRec(node.right, id);

        } else {
            // === Node target ditemukan ===

            // KASUS 1 & 2: Tidak punya anak, atau hanya punya satu anak
            if (node.left == null) return node.right;  // Tidak ada anak kiri
            if (node.right == null) return node.left;  // Tidak ada anak kanan

            // KASUS 3: Punya dua anak
            // Cari penerus in-order = node terkecil di subtree KANAN
            BSTNode penerus = cariNodeTerkecil(node.right);

            // Salin data penerus ke node ini
            node.data = penerus.data;

            // Hapus penerus dari subtree kanan
            node.right = deleteRec(node.right, penerus.data.employeeId);
        }

        return node;
    }

    // ================================================================
    //  TRAVERSAL
    // ================================================================

    // --- INORDER: Kiri → Root → Kanan (Menghasilkan urutan ID terurut) ---
    /**
     * Inorder Traversal: menghasilkan daftar pegawai terurut berdasarkan ID (kecil ke besar).
     */
    public void inorderTraversal() {
        System.out.println("\n--- Inorder Traversal (Urut berdasarkan ID) ---");
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(BSTNode node) {
        if (node == null) return;
        inorderRec(node.left);                         // 1. Kunjungi kiri
        System.out.println("  " + node.data);          // 2. Cetak node ini
        inorderRec(node.right);                        // 3. Kunjungi kanan
    }

    // --- PREORDER: Root → Kiri → Kanan (Struktur hierarki) ---
    /**
     * Preorder Traversal: cetak root lebih dulu, lalu subtree kiri, lalu kanan.
     */
    public void preorderTraversal() {
        System.out.println("\n--- Preorder Traversal (Root → Kiri → Kanan) ---");
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(BSTNode node) {
        if (node == null) return;
        System.out.println("  " + node.data);          // 1. Cetak node ini
        preorderRec(node.left);                        // 2. Kunjungi kiri
        preorderRec(node.right);                       // 3. Kunjungi kanan
    }

    // --- POSTORDER: Kiri → Kanan → Root (Bottom-up) ---
    /**
     * Postorder Traversal: kunjungi kedua subtree dulu, cetak node terakhir.
     */
    public void postorderTraversal() {
        System.out.println("\n--- Postorder Traversal (Kiri → Kanan → Root) ---");
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(BSTNode node) {
        if (node == null) return;
        postorderRec(node.left);                       // 1. Kunjungi kiri
        postorderRec(node.right);                      // 2. Kunjungi kanan
        System.out.println("  " + node.data);          // 3. Cetak node ini
    }

    // ================================================================
    //  FIND MIN & MAX
    // ================================================================

    /**
     * Mencari pegawai dengan ID terkecil.
     * Dalam BST, ID terkecil selalu ada di node paling KIRI.
     */
    public Employee findMin() {
        if (root == null) {
            System.out.println("  [!] BST masih kosong.");
            return null;
        }
        return cariNodeTerkecil(root).data;
    }

    /**
     * Mencari pegawai dengan ID terbesar.
     * Dalam BST, ID terbesar selalu ada di node paling KANAN.
     */
    public Employee findMax() {
        if (root == null) {
            System.out.println("  [!] BST masih kosong.");
            return null;
        }
        return cariNodeTerbesar(root).data;
    }

    // Helper: terus ke kiri sampai tidak bisa lagi
    private BSTNode cariNodeTerkecil(BSTNode node) {
        if (node.left == null) return node;
        return cariNodeTerkecil(node.left);
    }

    // Helper: terus ke kanan sampai tidak bisa lagi
    private BSTNode cariNodeTerbesar(BSTNode node) {
        if (node.right == null) return node;
        return cariNodeTerbesar(node.right);
    }
}
