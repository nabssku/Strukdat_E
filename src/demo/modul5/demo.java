package demo.modul5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class InventoryItem {
    private String name;
    private int stock;
    private double price;

    public InventoryItem(String name, int stock, double price) {
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public double getPrice() {
        return price;
    }

    public void addStock(int amount) {
        stock += amount;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void displayInfo() {
        System.out.printf("| %-20s | %-10d | Rp %-12.2f |\n", name, stock, price);
    }
}

class CollisionKey {
    private String key;

    public CollisionKey(String key) {
        this.key = key;
    }

    @Override
    public int hashCode() {
        return 10;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CollisionKey) {
            CollisionKey other = (CollisionKey) obj;
            return this.key.equals(other.key);
        }
        return false;
    }

    @Override
    public String toString() {
        return key;
    }
}

public class demo {
    static Scanner input = new Scanner(System.in);

    static HashMap<String, InventoryItem> inventory = new HashMap<>();

    static HashMap<String, String> users = new HashMap<>();
    static HashMap<String, ArrayList<String>> userDetails = new HashMap<>();
    static String loggedInUser = null;

    public static void main(String[] args) {
        seedData();

        boolean running = true;

        while (running) {
            if (loggedInUser == null) {
                running = preLoginMenu();
            } else {
                running = postLoginMenu();
            }
        }

        System.out.println("\nProgram selesai. Sistem dimatikan.");
    }

    static void seedData() {
        inventory.put("Tiket Ekonomi", new InventoryItem("Tiket Ekonomi", 50, 50000));
        inventory.put("Tiket Bisnis", new InventoryItem("Tiket Bisnis", 25, 100000));
        inventory.put("Tiket Eksekutif", new InventoryItem("Tiket Eksekutif", 10, 150000));

        users.put("admin@railway", "admin123");

        ArrayList<String> adminDetail = new ArrayList<>();
        adminDetail.add("Admin Railway");
        adminDetail.add("Malang");

        userDetails.put("admin@railway", adminDetail);
    }

    static boolean preLoginMenu() {
        System.out.println("\n====================================");
        System.out.println("       RAILWAY INVENTORY SYSTEM     ");
        System.out.println("====================================");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Pilih menu: ");

        int choice = readInt();

        switch (choice) {
            case 1:
                register();
                break;
            case 2:
                login();
                break;
            case 3:
                return false;
            default:
                System.out.println("Menu tidak valid.");
        }

        return true;
    }

    static boolean postLoginMenu() {
        System.out.println("\n====================================");
        System.out.println("       POST-LOGIN MENU              ");
        System.out.println("====================================");
        System.out.println("Login sebagai: " + loggedInUser);
        System.out.println("1. Add New Inventory Item");
        System.out.println("2. View Inventory List");
        System.out.println("3. Search Inventory Item");
        System.out.println("4. Increase Item Stock");
        System.out.println("5. Remove Inventory Item");
        System.out.println("6. Update Item Price");
        System.out.println("7. View Inventory Report");
        System.out.println("8. Demo Collision Handling");
        System.out.println("9. Logout");
        System.out.println("0. Exit");
        System.out.print("Pilih menu: ");

        int choice = readInt();

        switch (choice) {
            case 1:
                addInventoryItem();
                break;
            case 2:
                viewInventoryList();
                break;
            case 3:
                searchInventoryItem();
                break;
            case 4:
                increaseItemStock();
                break;
            case 5:
                removeInventoryItem();
                break;
            case 6:
                updateItemPrice();
                break;
            case 7:
                viewInventoryReport();
                break;
            case 8:
                demoCollisionHandling();
                break;
            case 9:
                logout();
                break;
            case 0:
                return false;
            default:
                System.out.println("Menu tidak valid.");
        }

        return true;
    }

    static void register() {
        System.out.println("\n=== REGISTER ===");

        System.out.print("Username: ");
        String username = input.nextLine();

        System.out.print("Password: ");
        String password = input.nextLine();

        System.out.print("Full Name: ");
        String fullName = input.nextLine();

        System.out.print("Address: ");
        String address = input.nextLine();

        if (users.containsKey(username)) {
            System.out.println("Registration Failed! Username sudah digunakan.");
            return;
        }

        if (!username.contains("@")) {
            System.out.println("Registration Failed! Username harus mengandung karakter '@'.");
            return;
        }

        if (password.length() < 8) {
            System.out.println("Registration Failed! Password minimal 8 karakter.");
            return;
        }

        users.put(username, password);

        ArrayList<String> detail = new ArrayList<>();
        detail.add(fullName);
        detail.add(address);

        userDetails.put(username, detail);

        System.out.println("Registration Successful!");
    }

    static void login() {
        System.out.println("\n=== LOGIN ===");

        System.out.print("Username: ");
        String username = input.nextLine();

        System.out.print("Password: ");
        String password = input.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            loggedInUser = username;

            ArrayList<String> detail = userDetails.get(username);
            String fullName = detail.get(0);
            String address = detail.get(1);

            System.out.println("Login Successful!");
            System.out.println("Welcome, " + fullName + "!");
            System.out.println("Address: " + address);
        } else {
            System.out.println("Login Failed!");
        }
    }

    static void logout() {
        System.out.println("\nUser " + loggedInUser + " berhasil logout.");
        loggedInUser = null;
    }

    static void addInventoryItem() {
        System.out.println("\n=== ADD NEW INVENTORY ITEM ===");

        System.out.print("Input item name: ");
        String itemName = input.nextLine();

        System.out.print("Input initial stock: ");
        int stock = readInt();

        System.out.print("Input price: ");
        double price = readDouble();

        inventory.put(itemName, new InventoryItem(itemName, stock, price));

        System.out.println("Item added successfully!");
    }

    static void viewInventoryList() {
        System.out.println("\n=== INVENTORY LIST ===");

        if (inventory.isEmpty()) {
            System.out.println("Inventory masih kosong.");
            return;
        }

        printTableHeader();

        for (Map.Entry<String, InventoryItem> entry : inventory.entrySet()) {
            InventoryItem item = entry.getValue();
            item.displayInfo();
        }

        printTableFooter();
    }

    static void searchInventoryItem() {
        System.out.println("\n=== SEARCH INVENTORY ITEM ===");

        System.out.print("Input item name: ");
        String itemName = input.nextLine();

        if (inventory.containsKey(itemName)) {
            InventoryItem item = inventory.get(itemName);

            System.out.println("Item ditemukan:");
            printTableHeader();
            item.displayInfo();
            printTableFooter();
        } else {
            System.out.println("Item tidak ditemukan.");
        }
    }

    static void increaseItemStock() {
        System.out.println("\n=== INCREASE ITEM STOCK ===");

        System.out.print("Input item name: ");
        String itemName = input.nextLine();

        if (!inventory.containsKey(itemName)) {
            System.out.println("Item tidak ditemukan.");
            return;
        }

        System.out.print("Input additional stock: ");
        int additionalStock = readInt();

        InventoryItem item = inventory.get(itemName);
        item.addStock(additionalStock);

        System.out.println("Stock berhasil ditambahkan.");
    }

    static void removeInventoryItem() {
        System.out.println("\n=== REMOVE INVENTORY ITEM ===");

        System.out.print("Input item name: ");
        String itemName = input.nextLine();

        if (inventory.containsKey(itemName)) {
            inventory.remove(itemName);
            System.out.println("Item berhasil dihapus.");
        } else {
            System.out.println("Item tidak ditemukan.");
        }
    }

    static void updateItemPrice() {
        System.out.println("\n=== UPDATE ITEM PRICE ===");

        System.out.print("Input item name: ");
        String itemName = input.nextLine();

        if (!inventory.containsKey(itemName)) {
            System.out.println("Item tidak ditemukan.");
            return;
        }

        System.out.print("Input new price: ");
        double newPrice = readDouble();

        InventoryItem oldItem = inventory.get(itemName);
        InventoryItem updatedItem = new InventoryItem(
                oldItem.getName(),
                oldItem.getStock(),
                newPrice
        );

        inventory.put(itemName, updatedItem);

        System.out.println("Harga item berhasil diupdate.");
    }

    static void viewInventoryReport() {
        System.out.println("\n=== INVENTORY REPORT ===");

        if (loggedInUser == null) {
            System.out.println("Akses ditolak. Silakan login terlebih dahulu.");
            return;
        }

        if (inventory.isEmpty()) {
            System.out.println("Inventory masih kosong.");
        } else {
            printTableHeader();

            for (Map.Entry<String, InventoryItem> entry : inventory.entrySet()) {
                InventoryItem item = entry.getValue();
                item.displayInfo();
            }

            printTableFooter();
        }

        ArrayList<String> detail = userDetails.get(loggedInUser);
        System.out.println("Report generated by: " + detail.get(0));
    }

    static void demoCollisionHandling() {
        System.out.println("\n=== DEMO COLLISION HANDLING ===");

        HashMap<CollisionKey, String> collisionMap = new HashMap<>();

        CollisionKey key1 = new CollisionKey("INV001");
        CollisionKey key2 = new CollisionKey("INV002");
        CollisionKey key3 = new CollisionKey("INV003");

        collisionMap.put(key1, "Tiket Ekonomi");
        collisionMap.put(key2, "Tiket Bisnis");
        collisionMap.put(key3, "Tiket Eksekutif");

        System.out.println("Semua key di bawah ini punya hashCode yang sama.");
        System.out.println("HashCode INV001: " + key1.hashCode());
        System.out.println("HashCode INV002: " + key2.hashCode());
        System.out.println("HashCode INV003: " + key3.hashCode());

        System.out.println("\nData tetap bisa disimpan dan diambil:");
        for (Map.Entry<CollisionKey, String> entry : collisionMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " | Value: " + entry.getValue());
        }

        System.out.println("\nKesimpulan: HashMap tetap bisa menangani collision.");
    }

    static int readInt() {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Input harus angka. Coba lagi: ");
            }
        }
    }

    static double readDouble() {
        while (true) {
            try {
                return Double.parseDouble(input.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Input harus angka. Coba lagi: ");
            }
        }
    }

    static void printTableHeader() {
        System.out.println("+----------------------+------------+---------------+");
        System.out.println("| Item Name            | Stock      | Price         |");
        System.out.println("+----------------------+------------+---------------+");
    }

    static void printTableFooter() {
        System.out.println("+----------------------+------------+---------------+");
    }
}