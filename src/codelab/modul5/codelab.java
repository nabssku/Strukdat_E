package codelab.modul5;
import java.util.HashMap;
import java.util.Map;


public class codelab {
    public static void main(String[] args) {

        // TODO 1: Membuat HashMap untuk menyimpan kontak
        HashMap<String, String> kontakStasiun = new HashMap<>();

        kontakStasiun.put("FATIH - Kepala Stasiun", "081234567890");
        kontakStasiun.put("WIRA - Customer Service", "081298765432");
        kontakStasiun.put("EGA - Keamanan", "081255555555");

        // TODO 2: Menampilkan seluruh isi kontak menggunakan entrySet()
        System.out.println("Daftar Kontak Stasiun");
        for (Map.Entry<String, String> entry : kontakStasiun.entrySet()) {
            System.out.println("Nama: " + entry.getKey()
                    + " | Telepon: " + entry.getValue());
        }

        // TODO 3: Cari nomor telepon berdasarkan nama menggunakan get()
        String namaDicari = "WIRA - Customer Service";
        String nomorTelepon = kontakStasiun.get(namaDicari);

        if (nomorTelepon != null) {
            System.out.println("\nNomor telepon " + namaDicari + ": " + nomorTelepon);
        } else {
            System.out.println("\nKontak " + namaDicari + " tidak ditemukan.");
        }

        // TODO 4: Cek apakah kontak ada menggunakan containsKey()
        String namaPengecekan = "Keamanan";

        if (kontakStasiun.containsKey(namaPengecekan)) {
            System.out.println("Kontak " + namaPengecekan + " tersedia di sistem.");
        } else {
            System.out.println("Kontak " + namaPengecekan + " tidak tersedia.");
        }

        // TODO 5: Hapus data kontak menggunakan remove()
        String namaDihapus = "FATIH - Kepala Stasiun";
        kontakStasiun.remove(namaDihapus);

        System.out.println("\nIsi kontak setelah menghapus " + namaDihapus + ":");
        System.out.println(kontakStasiun);
    }
}
