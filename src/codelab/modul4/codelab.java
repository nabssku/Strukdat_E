package codelab.modul4;

public class codelab {


    static class Node {
        String name;
        Node left;
        Node right;

        public Node(String name) {
            this.name = name;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public void insert(String name) {
        root = insertRec(root, name);
    }

    private Node insertRec(Node root, String name) {
        if (root == null) {
            root = new Node(name);
            return root;
        }

        if (name.compareTo(root.name) < 0) {
            root.left = insertRec(root.left, name);
        } else if (name.compareTo(root.name) > 0) {
            root.right = insertRec(root.right, name);
        }

        return root;
    }

    // ==================================================================================
    // PERMINTAAN TAMBAHAN (Extra Features)
    // ==================================================================================

    // 1. SEARCH (Mencari Tokoh)
    public boolean search(String name) {
        return searchRec(root, name);
    }

    private boolean searchRec(Node root, String name) {
        if (root == null)
            return false;
        if (root.name.equals(name))
            return true;

        if (name.compareTo(root.name) < 0)
            return searchRec(root.left, name);

        return searchRec(root.right, name);
    }

    // 2. FIND MIN & MAX (Tokoh Awal & Akhir Abjad)
    public String findMin() {
        return findMinRec(root);
    }

    private String findMinRec(Node root) {
        if (root.left == null) {
            return root.name;
        }
        return findMinRec(root.left);
    }

    public String findMax() {
        return findMaxRec(root);
    }

    private String findMaxRec(Node root) {
        if (root.right == null) {
            return root.name;
        }
        return findMaxRec(root.right);
    }

    // 3. DELETION (Menghapus Tokoh) -> ADVANCED
    public void delete(String name) {
        root = deleteRec(root, name);
    }

    private Node deleteRec(Node root, String name) {
        if (root == null)
            return root;

        if (name.compareTo(root.name) < 0) {
            root.left = deleteRec(root.left, name);
        } else if (name.compareTo(root.name) > 0) {
            root.right = deleteRec(root.right, name);
        } else {
            // Node Found
            // Case 1: No child or 1 child
            if (root.left == null)
                return root.right;
            if (root.right == null)
                return root.left;

            // Case 2: Two children
            root.name = findMinRec(root.right);
            root.right = deleteRec(root.right, root.name);
        }
        return root;
    }

    // ==================================================================================
    // TRAVERSALS
    // ==================================================================================

    // A) InOrder (Alphabetical / Chronological in BST)
    public void inOrder() {
        System.out.println("\n1. InOrder (Alphabetical Sort):");
        inOrderRec(root);
        System.out.println();
    }

    private void inOrderRec(Node root) {
        if (root != null) {
            inOrderRec(root.left);
            System.out.print(root.name + " -> ");
            inOrderRec(root.right);
        }
    }

    // B) PreOrder (Government Structure / Hierarchy)
    public void preOrder() {
        System.out.println("\n2. PreOrder (Royal Decree Structure):");
        preOrderRec(root);
        System.out.println();
    }

    private void preOrderRec(Node root) {
        if (root != null) {
            System.out.print(root.name + " -> ");
            preOrderRec(root.left);
            preOrderRec(root.right);
        }
    }

    // C) PostOrder (Historical Archives / Bottom-Up)
    public void postOrder() {
        System.out.println("\n3. PostOrder (Historical Archives):");
        postOrderRec(root);
        System.out.println();
    }

    private void postOrderRec(Node root) {
        if (root != null) {
            postOrderRec(root.left);
            postOrderRec(root.right);
            System.out.print(root.name + " -> ");
        }
    }

    public static void main(String[] args) {
        codelab majapahit = new codelab();

        // 1. Display Terminology
        System.out.println(">>> KITAB NEGARAKERTAGAMA: MAJAPAHIT DYNASTY <<<");
        System.out.println("[Theory] Root: Founding Father, Leaves: The Legacy...");

        // 2. Insert Historical Figures (BST Logic: A-Z)
        // Root: Raden Wijaya
        majapahit.insert("Raden Wijaya");

        // Left Branch (< R)
        majapahit.insert("Jayanegara"); // J < R
        majapahit.insert("Gajah Mada"); // G < J
        majapahit.insert("Kertanegara"); // K > J, K < R

        // Right Branch (> R)
        majapahit.insert("Tribhuwana"); // T > R

        // H < R, H < J, H > G -> Right of Gajah Mada
        majapahit.insert("Hayam Wuruk");

        // VISUALIZATION (ASCII)
        System.out.println("\n[Structure Visualization (BST Alphabetical)]");
        System.out.println("        Raden Wijaya (Root)");
        System.out.println("        /           \\");
        System.out.println("   Jayanegara    Tribhuwana");
        System.out.println("   /        \\");
        System.out.println("Gajah Mada  Kertanegara");
        System.out.println("    \\");
        System.out.println("   Hayam Wuruk");

        // 3. Execute Traversals
        majapahit.inOrder();
        majapahit.preOrder();
        majapahit.postOrder();

        // 4. Test New Features (Search, Min, Max, Delete)
        System.out.println("\n>>> UPDATING CHRONICLES (SEARCH & DELETE) <<<");

        // Search
        System.out.println("Is 'Gajah Mada' in the tree? " + (majapahit.search("Gajah Mada") ? "YES" : "NO"));
        System.out.println("Is 'Lembu Sora' in the tree? " + (majapahit.search("Lembu Sora") ? "YES" : "NO"));

        // Min/Max
        System.out.println("First Alphabetical Name: " + majapahit.findMin());
        System.out.println("Last Alphabetical Name:  " + majapahit.findMax());

        // Delete
        System.out.println("Removing 'Jayanegara' from history...");
        majapahit.delete("Jayanegara");
        majapahit.inOrder(); // Verify removal
    }
}