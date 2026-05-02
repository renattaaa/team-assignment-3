/**
 * Bagian 1: totalDurasi() Bagian 2: tampilkanMundur() 
 *
 * Nama   : Renata Ramadhanyandra
 * NIM    : NIM: 2902696426
 *
 * Nama : Dustin Ballqis Saputra
 * NIM : 2902730445
class Lagu {
    private String judul;
    private String penyanyi;
    private double durasi; // dalam menit

    public Lagu(String judul, String penyanyi, double durasi) {
        this.judul    = judul;
        this.penyanyi = penyanyi;
        this.durasi   = durasi;
    }

    public String getJudul()    { return judul; }
    public String getPenyanyi() { return penyanyi; }
    public double getDurasi()   { return durasi; }
}

public class PlaylistRekursif {

    /**
     * Tujuan        : Menghitung total durasi seluruh lagu dalam playlist secara rekursif.
     *
     * Base case     : n == 0
     *                 Tidak ada lagu yang perlu dijumlahkan → kembalikan 0.0
     *
     * Recursive case: Ambil durasi lagu ke-(n-1), lalu tambahkan dengan hasil
     *                 pemanggilan rekursif totalDurasi(list, n-1).
     *                 → list[n-1].getDurasi() + totalDurasi(list, n-1)
     *
     * Kompleksitas  : O(n)
     *                 Fungsi dipanggil sebanyak n+1 kali (n lagu + 1 base case).
     *                 Setiap pemanggilan melakukan 1 operasi penjumlahan → linear.
     */
    public static double totalDurasi(Lagu[] list, int n) {
        // Base case: tidak ada lagu tersisa
        if (n == 0) {
            return 0.0;
        }
        // Recursive case: durasi lagu ke-(n-1) + total sisa
        return list[n - 1].getDurasi() + totalDurasi(list, n - 1);
    }

    /**
     * Tujuan        : Menampilkan daftar lagu dari belakang (rekursif)
     *
     * Base case     : n == 0 → berhenti
     *
     * Recursive case: cetak lagu ke-(n-1), lalu panggil ke sisa data
     *
     * Kompleksitas  : O(n)
     */
    public static void tampilkanMundur(Lagu[] list, int n) {
        if (n == 0) {
            return;
        }

        Lagu lagu = list[n - 1];
        System.out.println(lagu.getJudul() + " - " + lagu.getPenyanyi() +
                           " (" + lagu.getDurasi() + " menit)");

        tampilkanMundur(list, n - 1);
    }

    public static void main(String[] args) {

        // --- Data dummy: 5 lagu ---
        Lagu[] playlist = {
            new Lagu("Perfect",      "Ed Sheeran", 4.23),
            new Lagu("Shivers",      "Ed Sheeran", 3.50),
            new Lagu("Fix You",      "Coldplay",   4.52),
            new Lagu("Yellow",       "Coldplay",   4.29),
            new Lagu("Viva La Vida", "Coldplay",   4.03)
        };

        System.out.println("=== BAGIAN 1: totalDurasi() ===");
        System.out.println("Jumlah lagu : " + playlist.length);

        // --- Pengukuran waktu eksekusi ---
        long mulai   = System.nanoTime();
        double total = totalDurasi(playlist, playlist.length);
        long selesai = System.nanoTime();

        long ms = (selesai - mulai) / 1_000_000;

        System.out.printf("Total durasi         : %.2f menit%n", total);
        System.out.println("Execution Time       : " + ms + " ms");
        System.out.println();
        System.out.println("--- Analisis Kompleksitas ---");
        System.out.println("Base case            : n == 0");
        System.out.println("Growth rate          : Linear");
        System.out.println("Kompleksitas waktu   : O(n)");

        // ===== TAMBAHAN BAGIAN 2 =====
        System.out.println();
        System.out.println("=== BAGIAN 2: tampilkanMundur() ===");

        long mulai2 = System.nanoTime();

        tampilkanMundur(playlist, playlist.length);

        long selesai2 = System.nanoTime();
        long ms2 = (selesai2 - mulai2) / 1_000_000;

        System.out.println("Execution Time       : " + ms2 + " ms");
        System.out.println();
        System.out.println("--- Analisis Kompleksitas ---");
        System.out.println("Base case            : n == 0");
        System.out.println("Growth rate          : Linear");
        System.out.println("Kompleksitas waktu   : O(n)");
    }
}
