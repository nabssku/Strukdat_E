package demo.modul4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * ============================================================
 * Kelas EmployeeBST
 * Mengelola data pegawai menggunakan Binary Search Tree (BST).
 *
 * Aturan BST:
 *   - ID lebih KECIL dari root → masuk ke KIRI
 *   - ID lebih BESAR dari root → masuk ke KANAN
 *
 * Fitur visualisasi pohon ditambahkan pada menu traversal
 * agar struktur BST bisa terlihat langsung di terminal.
 * ============================================================
 */
public class EmployeeBST {

    private BSTNode root;

    // ================================================================
    //  INSERT
    // ================================================================
    public void insert(Employee emp) {
        root = insertRec(root, emp);
    }

    private BSTNode insertRec(BSTNode node, Employee emp) {
        if (node == null) {
            System.out.println("  [+] Pegawai ditambahkan: " + emp);
            return new BSTNode(emp);
        }
        int cmp = emp.compareTo(node.data);
        if      (cmp < 0) node.left  = insertRec(node.left,  emp);
        else if (cmp > 0) node.right = insertRec(node.right, emp);
        else System.out.println("  [!] ID " + emp.employeeId + " sudah ada. Duplikat ditolak.");
        return node;
    }

    // ================================================================
    //  SEARCH
    // ================================================================
    public Employee search(int id) {
        BSTNode result = searchRec(root, id);
        return (result != null) ? result.data : null;
    }

    private BSTNode searchRec(BSTNode node, int id) {
        if (node == null) return null;
        if (node.data.employeeId == id) return node;
        return (id < node.data.employeeId)
                ? searchRec(node.left, id)
                : searchRec(node.right, id);
    }

    // ================================================================
    //  DELETE
    // ================================================================
    public void delete(int id) {
        if (search(id) == null) {
            System.out.println("  [!] Pegawai dengan ID " + id + " tidak ditemukan.");
            return;
        }
        root = deleteRec(root, id);
        System.out.println("  [-] Pegawai ID " + id + " berhasil dihapus.");
    }

    private BSTNode deleteRec(BSTNode node, int id) {
        if (node == null) return null;

        if      (id < node.data.employeeId) node.left  = deleteRec(node.left,  id);
        else if (id > node.data.employeeId) node.right = deleteRec(node.right, id);
        else {
            // Kasus 1 & 2: daun atau satu anak
            if (node.left  == null) return node.right;
            if (node.right == null) return node.left;

            // Kasus 3: dua anak → ganti dengan penerus in-order (terkecil di kanan)
            BSTNode penerus = cariNodeTerkecil(node.right);
            node.data  = penerus.data;
            node.right = deleteRec(node.right, penerus.data.employeeId);
        }
        return node;
    }

    // ================================================================
    //  TRAVERSAL — INORDER  (Kiri → Root → Kanan)
    //  Hasil: urutan ID dari kecil ke besar
    // ================================================================
    public void inorderTraversal() {
        if (root == null) { System.out.println("  [!] BST masih kosong."); return; }

        cetakVisualPohon();

        System.out.println("\n  Urutan kunjungan  :  Kiri → Root → Kanan");
        System.out.println("  ─────────────────────────────────────────────────");

        // Kumpulkan urutan kunjungan untuk ditampilkan sebagai alur
        List<String> urutan = new ArrayList<>();
        inorderKumpulkan(root, urutan);

        // Cetak alur panah
        System.out.print("  ");
        for (int i = 0; i < urutan.size(); i++) {
            System.out.print(urutan.get(i));
            if (i < urutan.size() - 1) System.out.print(" → ");
        }
        System.out.println();

        // Cetak detail tiap node
        System.out.println("\n  Detail data (urut ID kecil ke besar):");
        System.out.println("  ─────────────────────────────────────────────────");
        inorderCetak(root, 1);
        System.out.println();
    }

    private void inorderKumpulkan(BSTNode node, List<String> list) {
        if (node == null) return;
        inorderKumpulkan(node.left, list);
        list.add(String.valueOf(node.data.employeeId));
        inorderKumpulkan(node.right, list);
    }

    private void inorderCetak(BSTNode node, int[] nomor) {
        if (node == null) return;
        inorderCetak(node.left, nomor);
        System.out.printf("  %2d. %s%n", nomor[0]++, node.data);
        inorderCetak(node.right, nomor);
    }

    // Overload helper agar bisa passing counter
    private void inorderCetak(BSTNode node, int mulai) {
        int[] counter = {mulai};
        inorderCetakHelper(root, counter);
    }

    private void inorderCetakHelper(BSTNode node, int[] counter) {
        if (node == null) return;
        inorderCetakHelper(node.left, counter);
        System.out.printf("  %2d. %s%n", counter[0]++, node.data);
        inorderCetakHelper(node.right, counter);
    }

    // ================================================================
    //  TRAVERSAL — PREORDER  (Root → Kiri → Kanan)
    //  Hasil: urutan seperti struktur pohon (atas ke bawah)
    // ================================================================
    public void preorderTraversal() {
        if (root == null) { System.out.println("  [!] BST masih kosong."); return; }

        cetakVisualPohon();

        System.out.println("\n  Urutan kunjungan  :  Root → Kiri → Kanan");
        System.out.println("  ─────────────────────────────────────────────────");

        List<String> urutan = new ArrayList<>();
        preorderKumpulkan(root, urutan);

        System.out.print("  ");
        for (int i = 0; i < urutan.size(); i++) {
            System.out.print(urutan.get(i));
            if (i < urutan.size() - 1) System.out.print(" → ");
        }
        System.out.println();

        System.out.println("\n  Detail data (urutan preorder):");
        System.out.println("  ─────────────────────────────────────────────────");
        int[] counter = {1};
        preorderCetak(root, counter);
        System.out.println();
    }

    private void preorderKumpulkan(BSTNode node, List<String> list) {
        if (node == null) return;
        list.add(String.valueOf(node.data.employeeId));
        preorderKumpulkan(node.left, list);
        preorderKumpulkan(node.right, list);
    }

    private void preorderCetak(BSTNode node, int[] counter) {
        if (node == null) return;
        System.out.printf("  %2d. %s%n", counter[0]++, node.data);
        preorderCetak(node.left, counter);
        preorderCetak(node.right, counter);
    }

    // ================================================================
    //  TRAVERSAL — POSTORDER  (Kiri → Kanan → Root)
    //  Hasil: daun-daun duluan, root paling akhir
    // ================================================================
    public void postorderTraversal() {
        if (root == null) { System.out.println("  [!] BST masih kosong."); return; }

        cetakVisualPohon();

        System.out.println("\n  Urutan kunjungan  :  Kiri → Kanan → Root");
        System.out.println("  ─────────────────────────────────────────────────");

        List<String> urutan = new ArrayList<>();
        postorderKumpulkan(root, urutan);

        System.out.print("  ");
        for (int i = 0; i < urutan.size(); i++) {
            System.out.print(urutan.get(i));
            if (i < urutan.size() - 1) System.out.print(" → ");
        }
        System.out.println();

        System.out.println("\n  Detail data (urutan postorder):");
        System.out.println("  ─────────────────────────────────────────────────");
        int[] counter = {1};
        postorderCetak(root, counter);
        System.out.println();
    }

    private void postorderKumpulkan(BSTNode node, List<String> list) {
        if (node == null) return;
        postorderKumpulkan(node.left, list);
        postorderKumpulkan(node.right, list);
        list.add(String.valueOf(node.data.employeeId));
    }

    private void postorderCetak(BSTNode node, int[] counter) {
        if (node == null) return;
        postorderCetak(node.left, counter);
        postorderCetak(node.right, counter);
        System.out.printf("  %2d. %s%n", counter[0]++, node.data);
    }

    // ================================================================
    //  FIND MIN & MAX
    // ================================================================
    public Employee findMin() {
        if (root == null) { System.out.println("  [!] BST masih kosong."); return null; }
        return cariNodeTerkecil(root).data;
    }

    public Employee findMax() {
        if (root == null) { System.out.println("  [!] BST masih kosong."); return null; }
        return cariNodeTerbesar(root).data;
    }

    private BSTNode cariNodeTerkecil(BSTNode node) {
        return (node.left == null) ? node : cariNodeTerkecil(node.left);
    }

    private BSTNode cariNodeTerbesar(BSTNode node) {
        return (node.right == null) ? node : cariNodeTerbesar(node.right);
    }

    public void cetakVisualPohon() {
        System.out.println();
        System.out.println("  ╔══════════════════════════════════════════════════╗");
        System.out.println("  ║           VISUALISASI POHON BST                  ║");
        System.out.println("  ║   Baca: atas = kanan (ID besar)                  ║");
        System.out.println("  ║         bawah = kiri  (ID kecil)                 ║");
        System.out.println("  ╚══════════════════════════════════════════════════╝");
        System.out.println();

        if (root == null) {
            System.out.println("  (pohon kosong)");
            return;
        }

        // Cetak pohon secara rekursif dengan indentasi
        cetakPohonRec(root, "", true, true);
        System.out.println();

        // Cetak juga versi per level (BFS) agar lebih mudah dipahami
        cetakPerLevel();
    }

    /**
     * Mencetak pohon secara rekursif dengan garis sambung.
     * Pohon "diputar" 90 derajat: kanan di atas, kiri di bawah.
     *
     * @param node    Node yang sedang diproses
     * @param prefix  Indentasi/garis dari level atas
     * @param isRoot  Apakah node ini root?
     * @param isRight Apakah node ini anak kanan dari induknya?
     */
    private void cetakPohonRec(BSTNode node, String prefix, boolean isRoot, boolean isRight) {
        if (node == null) return;

        // Cetak anak kanan duluan (tampil di atas di terminal)
        if (node.right != null) {
            String sambungan = isRoot ? "        " : (isRight ? "│       " : "        ");
            cetakPohonRec(node.right, prefix + sambungan, false, true);
        }

        // Cetak node saat ini
        String konektor;
        String label;

        if (isRoot) {
            konektor = "";
            label    = "[R]"; // Root
        } else if (isRight) {
            konektor = "┌───";
            label    = "[r]"; // right child
        } else {
            konektor = "└───";
            label    = "[L]"; // left child
        }

        System.out.printf("  %s%s%s ID:%-4d  %-15s  %s%n",
                prefix,
                konektor,
                label,
                node.data.employeeId,
                node.data.name,
                node.data.position);

        // Cetak anak kiri setelahnya (tampil di bawah di terminal)
        if (node.left != null) {
            String sambungan = isRoot ? "        " : (isRight ? "        " : "│       ");
            cetakPohonRec(node.left, prefix + sambungan, false, false);
        }
    }

    /**
     * Mencetak pohon per level (BFS / Level-Order).
     * Berguna untuk melihat dengan jelas node mana yang ada di level mana.
     */
    private void cetakPerLevel() {
        System.out.println("  ┌─────────────────────────────────────────────────┐");
        System.out.println("  │  Tampilan per Level (kiri ke kanan)              │");
        System.out.println("  └─────────────────────────────────────────────────┘");

        Queue<BSTNode> antrian = new LinkedList<>();
        antrian.add(root);
        int level = 0;

        while (!antrian.isEmpty()) {
            int jumlahLevel = antrian.size();
            System.out.printf("  Level %d : ", level);

            for (int i = 0; i < jumlahLevel; i++) {
                BSTNode current = antrian.poll();

                // Cetak ID dengan nama singkat
                String namaSingkat = current.data.name.split(" ")[0]; // ambil nama depan
                System.out.printf("[%d/%s]", current.data.employeeId, namaSingkat);

                if (i < jumlahLevel - 1) System.out.print("  ");

                if (current.left  != null) antrian.add(current.left);
                if (current.right != null) antrian.add(current.right);
            }
            System.out.println();
            level++;
        }
        System.out.println();
    }
}
