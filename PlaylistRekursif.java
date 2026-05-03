/**
 * Tugas Kelompok 3 (Week 8 - Rekursif)
 * Deskripsi: Implementasi sistem playlist menggunakan metode rekursif.
 * Bagian 1: totalDurasi() 
 * Nama: Renata Ramadhanyandra
 * Bagian 2: tampilkanMundur()
 * Nama: Dustin Ballqis Saputra
 * Bagian 3: CariDurasiTerpanjang()
 * Nama: Daffa Fathur Rahman
 * Bagian 4: Integrasi dan Testing
 * Nama: Hendrikus Avero Widarto
 */

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
     *
     * Kompleksitas  : O(n)
     */
    public static double totalDurasi(Lagu[] list, int n) {
        if (n == 0) return 0.0;
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
        if (n == 0) return;

        Lagu lagu = list[n - 1];
        System.out.println(lagu.getJudul() + " - " + lagu.getPenyanyi() + " (" + lagu.getDurasi() + " menit)");

        tampilkanMundur(list, n - 1);
    }

    /**
     * Bagian 3: cariDurasiTerpanjang() (Rekursif)
     */
    public static double cariDurasiTerpanjang(Lagu[] list, int n) {
        if (n == 1) {
            return list[0].getDurasi();
        }

        double maxSisa = cariDurasiTerpanjang(list, n - 1);

        if (list[n - 1].getDurasi() > maxSisa) {
            return list[n - 1].getDurasi();
        } else {
            return maxSisa;
        }
    }

    public static void main(String[] args) {

        // Data dummy: 10 lagu
        Lagu[] playlist = {
            new Lagu("Perfect", "Ed Sheeran", 4.23),
            new Lagu("Shivers", "Ed Sheeran", 3.50),
            new Lagu("Fix You", "Coldplay", 4.52),
            new Lagu("Yellow", "Coldplay", 4.29),
            new Lagu("Viva La Vida", "Coldplay", 4.03),
            new Lagu("Photograph", "Ed Sheeran", 4.18),
            new Lagu("Paradise", "Coldplay", 4.38),
            new Lagu("Hymn for the Weekend", "Coldplay", 4.20),
            new Lagu("Shape of You", "Ed Sheeran", 3.53),
            new Lagu("Adventure of a Lifetime", "Coldplay", 4.23)
        };

        // --- EKSEKUSI BAGIAN 1 ---
        System.out.println("=== BAGIAN 1: totalDurasi() ===");
        long mulai1 = System.nanoTime();
        double total = totalDurasi(playlist, playlist.length);
        long selesai1 = System.nanoTime();
        System.out.printf("Total durasi         : %.2f menit%n", total);
        System.out.println("Execution Time       : " + (selesai1 - mulai1) / 1_000_000 + " ms");

        // --- EKSEKUSI BAGIAN 2 ---
        System.out.println("\n=== BAGIAN 2: tampilkanMundur() ===");
        long mulai2 = System.nanoTime();
        tampilkanMundur(playlist, playlist.length);
        long selesai2 = System.nanoTime();
        System.out.println("Execution Time       : " + (selesai2 - mulai2) / 1_000_000 + " ms");

        // --- EKSEKUSI BAGIAN 3 ---
        System.out.println("\n=== BAGIAN 3: cariDurasiTerpanjang() ===");
        long mulai3 = System.nanoTime();
        double max = cariDurasiTerpanjang(playlist, playlist.length);
        long selesai3 = System.nanoTime();
        System.out.printf("Durasi terpanjang    : %.2f menit%n", max);
        System.out.println("Execution Time       : " + (selesai3 - mulai3) / 1_000_000 + " ms");

        System.out.println("\n--- Analisis Kompleksitas Bagian 3 ---");
        System.out.println("Growth rate          : Linear");
        System.out.println("Kompleksitas waktu   : O(n)");

        int[] ukuran = {3, 5, 10};

        for (int n : ukuran) {

            System.out.println("\n=== ANALISIS REKURSIF PLAYLIST ===");
            System.out.println("Jumlah lagu : " + n);

            long s1 = System.nanoTime();
            double totalN = totalDurasi(playlist, n);
            long e1 = System.nanoTime();

            long s3 = System.nanoTime();
            double maxN = cariDurasiTerpanjang(playlist, n);
            long e3 = System.nanoTime();

            System.out.println("Total durasi : " + String.format("%.2f", totalN) + " menit");
            System.out.println("Durasi terpanjang : " + maxN + " menit");

            System.out.println("\nDaftar lagu (ditampilkan terbalik):");
            long s2 = System.nanoTime();
            tampilkanMundur(playlist, n);
            long e2 = System.nanoTime();

            double timeTotal = (e1 - s1) / 1_000_000.0;
            double timeMundur = (e2 - s2) / 1_000_000.0;
            double timeMax = (e3 - s3) / 1_000_000.0;

            System.out.printf("\nExecution Time (totalDurasi): %.6f ms%n", timeTotal);
            System.out.printf("Execution Time (tampilkanMundur): %.6f ms%n", timeMundur);
            System.out.printf("Execution Time (cariDurasiTerpanjang): %.6f ms%n", timeMax);
        }
    }
}